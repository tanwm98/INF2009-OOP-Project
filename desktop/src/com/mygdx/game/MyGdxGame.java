package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class MyGdxGame extends Game { // Rename the class to MyGdxGame
    private ScreenManager screenManager;
    private EntityManager entityManager;
    private Player player;
    private Entity ball;
    private Entity paddle;
    private int blockWidth = 63, blockHeight = 20; //set the width and height of the blocks



    @Override
    public void create() {
        screenManager = new ScreenManager(this);
        // Creating the MainMenuScreen
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        // Manage the MainMenuScreen
        screenManager.newScreen(mainMenuScreen);

        entityManager = new EntityManager();
        player = new Player();
        ball = new Ball(50,20,10,5,5,Color.WHITE,true);
        paddle = new Paddle(100,20,300,100,20,Color.WHITE,false);
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight+10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                entityManager.addEntity(new Block(x,y,blockWidth,blockHeight, Color.WHITE));
                //add blocks to the entity manager
            }
        }
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
        //entityManager.detect();
    }

    @Override
    public void dispose() {
        screenManager.dispose();
        entityManager.dispose();
    }

}
