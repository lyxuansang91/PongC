package com.hungle.pongc;

import com.badlogic.gdx.Game;
import com.hungle.pongc.helpers.AssetLoader;
import com.hungle.pongc.screens.GameScreen;
import com.hungle.pongc.screens.SplashScreen;

public class CirclePongGame extends Game{

    private ActionResolver actionresolver;


    public CirclePongGame(ActionResolver actionresolver) {
    this.actionresolver = actionresolver;
    }

    @Override
    public void create() {
        AssetLoader.load();

        setScreen(new SplashScreen(this,actionresolver));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
