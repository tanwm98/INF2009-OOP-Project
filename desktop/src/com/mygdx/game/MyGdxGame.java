package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;

    @Override
    public void create() {
    	 // Initialize the ScreenManager
    	screenManager = new ScreenManager(this);
        // Creating the MainMenuScreen
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this); 
        // Manage the MainMenuScreen
        screenManager.newScreen(mainMenuScreen);
        
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }

    @Override
    public void render() {
        super.render(); 
    }

    @Override
    public void dispose() {
        screenManager.dispose(); 
    }
}
