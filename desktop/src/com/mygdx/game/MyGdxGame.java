package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.AIControlManager;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Managers.ScreenManager;


public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;
    private EntityManager entityManager;
    private AIControlManager aiControlManager;
    private OutputManager outputManager;
    private InputManager inputManager;

    private GameScreen gameScreen;
    private Player player;


    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.pushScreen(mainMenuScreen);
        entityManager = new EntityManager();
        aiControlManager = new AIControlManager();
        gameScreen = new GameScreen(this, player);
        player = new Player();
    }

    public GameScreen getGameScreen() {
        if (gameScreen == null) {
            gameScreen = new GameScreen(this);
        }
        return gameScreen;
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

    public Player getPlayer() {
        return player;
    }

    public InputManager getinputManager(){
    	if(inputManager == null) {
    	    inputManager = new InputManager();
    	}
    	return inputManager;
    }
    // For GameScreen
    public void resetGameScreen() {
//        GameScreen gameScreen = new GameScreen(this);
//        gameScreen.resetGame();
//        this.setScreen(gameScreen);
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
