package com.hungle.pongc.screens.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.hungle.pongc.Tweens.Value;
import com.hungle.pongc.Tweens.ValueAccessor;
import com.hungle.pongc.configuration.Configuration;
import com.hungle.pongc.helpers.AssetLoader;
import com.hungle.pongc.ui.MuteButton;
import com.hungle.pongc.ui.SimpleButton;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by ManuGil on 18/01/15.
 */
public class MenuObject {


    private final Rectangle rectangle;
    private final int buttonSize = 75;
    private MenuWorld world;

    private ArrayList<SimpleButton> menuButtons = new ArrayList<SimpleButton>();
    private SimpleButton playButton, rankButton, shareButton, achieveButton, rateButton;
    private MuteButton volumeButton;
    private Pad pad;
    public Value r1 = new Value();
    private TweenManager manager;

    public MenuObject(MenuWorld world, float gameWidth,float  gameHeight) {
        this.world = world;
        this.rectangle = new Rectangle(0,0,gameWidth, gameHeight);

        pad = new Pad(world);
        pad.start();
        pad.clickLeft();

        this.playButton = new SimpleButton(world, this.rectangle.width / 2.0F,(this.rectangle.height / 2.0F)+90, 250, 250, AssetLoader.playButtonUp, AssetLoader.playButtonDown, Color.WHITE);
        this.rankButton = new SimpleButton(world, this.rectangle.width /2f - 10-(buttonSize/2),this.rectangle.height/2 + 220f+80, this.buttonSize, this.buttonSize, AssetLoader.rankButtonUp, AssetLoader.rankButtonDown, Color.WHITE);
        this.shareButton = new SimpleButton(world, this.rectangle.width /2f - 30 - (buttonSize*1.5f), this.rectangle.height/2 + 220f+80, this.buttonSize, this.buttonSize, AssetLoader.shareButtonUp, AssetLoader.shareButtonDown, Color.WHITE);
        this.achieveButton = new SimpleButton(world, this.rectangle.width /2f +10 + (buttonSize/2), this.rectangle.height/2 + 220f+80, this.buttonSize, this.buttonSize, AssetLoader.achieveButtonUp, AssetLoader.achieveButtonDown, Color.WHITE);
        this.rateButton = new SimpleButton(world, this.rectangle.width /2f +30 + (buttonSize*1.5f),this.rectangle.height/2 + 220f+80, this.buttonSize, this.buttonSize, AssetLoader.rateButtonUp, AssetLoader.rateButtonDown, Color.WHITE);
        this.menuButtons.add(this.playButton);
        this.menuButtons.add(this.rankButton);
        this.menuButtons.add(this.shareButton);
        this.menuButtons.add(this.achieveButton);
        this.menuButtons.add(this.rateButton);

        this.volumeButton = new MuteButton(20 + this.buttonSize,20+ this.buttonSize, this.buttonSize, this.buttonSize, AssetLoader.soundI, AssetLoader.muteI, pad.getSprite().getColor());


        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        r1.setValue(-500);

        for(int i = 0;i<menuButtons.size();i++){
            menuButtons.get(i).start();
        }
        volumeButton.start();


        Tween.to(r1, -1, 0.5f).target(world.gameHeight/2-350).repeatYoyo(0, 0)
                .ease(TweenEquations.easeOutSine).start(manager);
    }


    public void update(float delta) {
        manager.update(delta);
        pad.update(delta);
        for(int i = 0;i<menuButtons.size();i++){
            menuButtons.get(i).update(delta);
        }
        volumeButton.update(delta);
        if(Math.random()<0.01f){
            pad.clickLeft();
        }
        if(Math.random()<0.01f){
            pad.clickRight();
        }

        //Gdx.app.log("Pad", pad.getPosition().toString());

    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, ShaderProgram fontShader){
        for(int i = 0;i<menuButtons.size();i++){
            menuButtons.get(i).draw(batch);
        }
        volumeButton.draw(batch);
        batch.setShader(fontShader);
        AssetLoader.font.getData().setScale(1.5f,-1.5f);
        AssetLoader.font.setColor(pad.getSprite().getColor());
        AssetLoader.font.draw(batch, Configuration.gameName, (world.gameWidth / 2)
                - (19.5f * (Configuration.gameName.length())), r1.getValue());

        batch.setShader(fontShader);
        AssetLoader.font2.setColor(pad.getSprite().getColor());
        AssetLoader.font2.draw(
                batch,
                "Score: " + AssetLoader.getScore(),
                (world.gameWidth / 2)
                        - (7f * 1.8f * (("Score: " + AssetLoader.getScore())
                        .length() - 0.9f)), r1.getValue()+120);
        AssetLoader.font2.draw(
                batch,
                "Highscore: " + AssetLoader.getHighScore(),
                (world.gameWidth / 2)
                        - (7f * 1.8f * (("Highscore: " + AssetLoader
                        .getHighScore()).length() - 0.9f)),
                r1.getValue()+170);
        AssetLoader.font2
                .draw(batch,
                        "Games Played: " + AssetLoader.getGamesPlayed(),
                        (world.gameWidth / 2)
                                - (7.5f * 1.8f * (("Games Played: " + AssetLoader
                                .getGamesPlayed()).length() - 0.9f)),
                        r1.getValue()+ 220);
        batch.setShader(null);
        pad.render(batch,shapeRenderer);
    }

    public ArrayList<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    public Pad getPad() {
        return pad;
    }

    public void end(){
        r1.setValue(world.gameHeight/2-350);
        Tween.to(r1, -1, 0.5f).target(-500).repeatYoyo(0, 0)
                .ease(TweenEquations.easeOutSine).start(manager);
    }

    public MuteButton getVolumeButton() {
        return volumeButton;
    }
}
