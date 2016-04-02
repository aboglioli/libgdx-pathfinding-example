package com.kiriost.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.kiriost.game.screen.GameScreen;
import com.kiriost.game.screen.MenuScreen;

public class AppGame extends Game {
    private Screen menuScreen;
    private Screen gameScreen;

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);

        this.setScreen(gameScreen);
    }
}
