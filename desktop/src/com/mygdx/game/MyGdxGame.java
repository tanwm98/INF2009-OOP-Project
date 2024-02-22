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


    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.pushScreen(mainMenuScreen);
        entityManager = new EntityManager();
        aiControlManager = new AIControlManager();
    }
    public ScreenManager getScreenManager() {
        return screenManager;
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