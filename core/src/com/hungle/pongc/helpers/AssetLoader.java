package com.hungle.pongc.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture logoTexture, pad, dot,arrowLT,arrowRT, soundT, muteT;
    public static TextureRegion logo,arrowL,arrowR,soundI,muteI;
    public static BitmapFont font, font1, font2;
    private static Preferences prefs;
    public static Sound sound;
    public static Texture ball;
    public static TextureRegion playBU;
    public static TextureRegion playBD;
    private static Texture buttonsT;
    public static TextureRegion playButtonDown, playButtonUp, rankButtonUp, rankButtonDown, shareButtonUp, shareButtonDown,achieveButtonUp,
            achieveButtonDown,rateButtonUp,rateButtonDown;
    public static Sound bounce;
    public static Music music;

    public static void load() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));

        logoTexture = new Texture(Gdx.files.internal("logo1.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        buttonsT = new Texture(Gdx.files.internal("buttons.png"));
        buttonsT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        arrowLT = new Texture(Gdx.files.internal("arrow.png"));
        arrowLT.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        soundT = new Texture(Gdx.files.internal("sound.png"));
        soundT.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        muteT = new Texture(Gdx.files.internal("mute.png"));
        muteT.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        soundI= new TextureRegion(soundT, 0, 0,soundT.getWidth(),soundT.getHeight());
        muteI = new TextureRegion(muteT, 0, 0,muteT.getWidth(),muteT.getHeight());

        soundI.flip(false, true);
         muteI.flip(false, true);

        arrowL = new TextureRegion(arrowLT, 0, 0,arrowLT.getWidth(),arrowLT.getHeight());
        arrowR = new TextureRegion(arrowLT, 0, 0,arrowLT.getWidth(),arrowLT.getHeight());

        arrowL.flip(false, true);
        arrowR.flip(true, true);

        //CROP BUTTONS
        playButtonUp = new TextureRegion(buttonsT, 0, 0, 240, 240);
        playButtonDown = new TextureRegion(buttonsT, 0, 240, 240, 240);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);
        rankButtonUp = new TextureRegion(buttonsT, 240, 0, 240, 240);
        rankButtonDown = new TextureRegion(buttonsT, 240, 240, 240, 240);
        rankButtonUp.flip(false, true);
        rankButtonDown.flip(false, true);
        shareButtonUp = new TextureRegion(buttonsT, 720, 0, 240, 240);
        shareButtonDown = new TextureRegion(buttonsT, 720, 240, 240, 240);
        shareButtonUp.flip(false, true);
        shareButtonDown.flip(false, true);
        achieveButtonUp = new TextureRegion(buttonsT, 960, 0, 240, 240);
        achieveButtonDown = new TextureRegion(buttonsT, 960, 240, 240, 240);
        achieveButtonUp.flip(false, true);
        achieveButtonDown.flip(false, true);
        rateButtonUp = new TextureRegion(buttonsT, 480, 0, 240, 240);
        rateButtonDown = new TextureRegion(buttonsT, 480, 240, 240, 240);
        rateButtonUp.flip(false, true);
        rateButtonDown.flip(false, true);

        pad = new Texture(Gdx.files.internal("pad.png"));
        pad.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        dot = new Texture(Gdx.files.internal("dot.png"));
        dot.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        ball = new Texture(Gdx.files.internal("ball.png"));
        ball.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        logo = new TextureRegion(logoTexture, 0, 0, logoTexture.getWidth(),logoTexture.getHeight());
        logo.flip(false, false);

        Texture tfont = new Texture(Gdx.files.internal("font.png"), true);
        tfont.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);

        // FONT
        font = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), false);
        font.getData().setScale(3f, -3f);
        font.setColor(Color.WHITE);

        font1 = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), false);
        font1.getData().setScale(1.3f, -1.3f);
        font1.setColor(Color.WHITE);

        font2 = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), false);
        font2.getData().setScale(0.9f, -0.9f);
        font2.setColor(Color.WHITE);

        // MENU BG TEXTURE

        prefs = Gdx.app.getPreferences("CirclePong");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("games")) {
            prefs.putInteger("games", 0);
        }

        sound = Gdx.audio.newSound(Gdx.files.internal("sound.wav"));
        bounce= Gdx.audio.newSound(Gdx.files.internal("bounce.wav"));



    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static void setScore(int val) {
        prefs.putInteger("score", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void setVolume(boolean val) {
        prefs.putBoolean("volume", val);
        prefs.flush();
    }

    public static boolean getVolume() {
        return prefs.getBoolean("volume");
    }
    public static int getScore() {
        return prefs.getInteger("score");
    }

    public static void addGamesPlayed() {
        prefs.putInteger("games", prefs.getInteger("games") + 1);
        prefs.flush();
    }

    public static int getGamesPlayed() {
        return prefs.getInteger("games");
    }

    public static void dispose() {
        font.dispose();
        arrowLT.dispose();
        setScore(0);
        music.dispose();
        bounce.dispose();
        logoTexture.dispose();
        sound.dispose();

    }
}
