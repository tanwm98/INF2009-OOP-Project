package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.MiniGameScreen;

import java.util.Stack;

public class ScreenManager extends MyGdxGame {
    private final MyGdxGame game; 
    private final Stack<Screen> screens; // Instantiated to keep track of the screens\
    private static ScreenManager instance;

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

    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen screenToPop = screens.pop();
            System.out.println(screenToPop.getClass().getSimpleName());
            if (!screens.isEmpty()) {
                Screen nextScreen = screens.peek();
                game.setScreen(nextScreen);
                
                if (nextScreen instanceof MiniGameScreen) {
                    ((MiniGameScreen)nextScreen).resumeGame();
                }
            } else {
                System.out.println("No more screens to display.");
            }
        }
    }
    
    public static ScreenManager getInstance(MyGdxGame game) {
        if (instance == null) {
            instance = new ScreenManager(game);
        }
        return instance;
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
