package com.kiriost.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.kiriost.game.screen.GameScreen;

public class AppGame extends Game {
    private Screen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);
    }
}
