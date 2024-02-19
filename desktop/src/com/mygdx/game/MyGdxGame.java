package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;
    private EntityManager entityManager;
    


    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.newScreen(mainMenuScreen);
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
        super.render(); // Important to call the parent method
        if (entityManager != null) {
            entityManager.moveEntities();
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