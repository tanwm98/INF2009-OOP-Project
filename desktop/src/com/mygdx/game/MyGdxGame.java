package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;
    private EntityManager entityManager;
    private Player player;
    private Entity ball;
    private Entity paddle;


    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        // Creating the MainMenuScreen
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.newScreen(mainMenuScreen);

        entityManager = new EntityManager();
        player = new Player();
        ball = new Ball(5,5,50,5,5,true);
        paddle = new Paddle(100,50,100,100,20,false);
        entityManager.addEntity(ball);
        entityManager.addEntity(paddle);
        
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
        super.render();
        entityManager.renderEntities();
        entityManager.moveEntities();
    }

    @Override
    public void dispose() {
        screenManager.dispose(); 
    }
}
