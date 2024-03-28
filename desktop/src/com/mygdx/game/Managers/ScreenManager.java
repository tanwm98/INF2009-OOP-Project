package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;

import java.util.Stack;

public class ScreenManager extends MyGdxGame {
    private final MyGdxGame game; 
    private final Stack<Screen> screens; // Instantiated to keep track of the screens\

    public ScreenManager(MyGdxGame game) {
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

	/*
	 * public void popToOrthoScreen() { while (!screens.isEmpty() &&
	 * !(screens.peek() OrthoScreen)) { Screen oldScreen = screens.pop();
	 * oldScreen.dispose(); } if (!screens.isEmpty()) {
	 * game.setScreen(screens.peek()); } else { // If there are no more screens
	 * left, set OrthoScreen as the current screen OrthoScreen orthoScreen = new
	 * OrthoScreen(game); game.setScreen(orthoScreen); } }
	 */
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
    //public Screen getCurrentScreen() {
       // return screens.isEmpty() ? null : screens.peek();
    //}

}
