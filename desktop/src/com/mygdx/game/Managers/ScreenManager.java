package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;

import java.util.Stack;

public class ScreenManager extends MyGdxGame {
    private final Game game; 
    private final Stack<Screen> screens; // Instantiated to keep track of the screens

    public ScreenManager(Game game) {
        this.game = game;
        this.screens = new Stack<>();
    }

    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            screens.peek().hide();
        }
        screens.push(screen);
        game.setScreen(screen);
    }

    public void popScreen() {
        if (screens.size() > 1) {
            Screen oldScreen = screens.pop();
            oldScreen.dispose();
            game.setScreen(screens.peek());
        } else {
        	Gdx.app.exit();
        }
    }

    public void setScreen(Screen screen) {
        for (Screen s : screens) {
            s.dispose();
        }
        screens.clear();
        screens.push(screen);
        game.setScreen(screen);
    }

    public void dispose() {
        for (Screen s : screens) {
            s.dispose();
        }
    }
}
