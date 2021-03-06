package com.hungle.pongc.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hungle.pongc.gameobjects.Ball;
import com.hungle.pongc.gameobjects.CenterCircle;
import com.hungle.pongc.gameobjects.Pad;
import com.hungle.pongc.helpers.AssetLoader;

public class GameRenderer {

    private OrthographicCamera cam;
    private final GameWorld world;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private ShaderProgram fontShader;

    private Pad pad;
    private Ball ball;
    private CenterCircle centerCircle;
    
    private GlyphLayout l;

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        initObjects();
        initFont();
    }

    private void initObjects() {
        pad = world.getPad();
        ball = world.getBall();
        centerCircle = world.getCenterCircle();
    }

    private void initFont() {
        fontShader = new ShaderProgram(Gdx.files.internal("font.vert"),
                Gdx.files.internal("font.frag"));
        if (!fontShader.isCompiled()) {
            Gdx.app.error("fontShader",
                    "compilation failed:\n" + fontShader.getLog());
        }
    }

    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(world.colorManager.getColor().r,world.colorManager.getColor().g,world.colorManager.getColor().b,world.colorManager.getColor().a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.app.log("D ",debugRenderer.toString());

        batch.begin();
        world.getArrowL().draw(batch);
        world.getArrowR().draw(batch);
        world.getVolumeButton().draw(batch);
        centerCircle.render(batch, shapeRenderer);
        drawScore();
        pad.render(batch, shapeRenderer);
        ball.render(batch, shapeRenderer);

        batch.end();

    }

    private void drawScore() {
        Gdx.gl.glEnable(GL20.GL_BLEND);

        batch.setShader(fontShader);
        AssetLoader.font.setColor(Color.valueOf(world.colorManager.getColor().toString()));
        
        l = new GlyphLayout(AssetLoader.font, world.getScore()+"");
        float fontX = world.gameWidth / 2 - l.width / 2 + 5;
        float fontY = world.gameHeight / 2 + l.height / 2 - 15;
        AssetLoader.font.draw(batch, "" + world.getScore(), fontX, fontY);


        batch.setShader(null);
    }
}
