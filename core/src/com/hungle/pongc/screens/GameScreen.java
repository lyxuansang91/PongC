package com.hungle.pongc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.hungle.pongc.ActionResolver;
import com.hungle.pongc.CirclePongGame;
import com.hungle.pongc.gameworld.GameRenderer;
import com.hungle.pongc.gameworld.GameWorld;
import com.hungle.pongc.helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    public float sW = Gdx.graphics.getWidth();
    public float sH = Gdx.graphics.getHeight();
    public float gameWidth = 720;
    public float gameHeight = sH / (sW / gameWidth);

    public GameScreen(CirclePongGame cpgame, ActionResolver actionResolver) {
        Gdx.app.log("GameScreen", "Attached");
        Gdx.app.log("GameWidth " + gameWidth, "GameHeight " + gameHeight);
        world = new GameWorld(cpgame, actionResolver, gameWidth, gameHeight);
        Gdx.input.setInputProcessor(new InputHandler(world, sW / gameWidth, sH
                / gameHeight));
        renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }


    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resize");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {

    }
}
