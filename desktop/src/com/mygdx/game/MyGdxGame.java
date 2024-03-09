package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.ScreenManager;


public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;
    private EntityManager entityManager;
    private AIControlManager aiControlManager;
    private OutputManager outputManager;
    private InputManager inputManager;


    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.pushScreen(mainMenuScreen);
        entityManager = new EntityManager();
        aiControlManager = new AIControlManager();
        outputManager = new OutputManager();
        inputManager = new InputManager();
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    
    public OutputManager getoutputManager(){
    	if(outputManager == null) {
    	    outputManager = new OutputManager();
    	}
    	return outputManager;
    }
    
    public InputManager getinputManager(){
    	if(inputManager == null) {
    	    inputManager = new InputManager();
    	}
    	return inputManager;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
        super.render(); // Important to call the parent method
        if (entityManager != null) {
            aiControlManager.moveEntities();
            entityManager.renderEntities();
        }
    }

    @Override
    public void dispose() {
        screenManager.dispose();
        if (entityManager != null) {
            entityManager.dispose();
        }
    }

}