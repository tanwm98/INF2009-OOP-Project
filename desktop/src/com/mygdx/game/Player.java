package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player extends Entity implements IMovement{

    private int lives,score;
    private boolean isAlive;
    private OrthographicCamera camera; //won't be used for the demo
    public Player(){
        lives = 3;
        score = 0;
        isAlive = true;

    }

    public void death()
    {
        lives--;
        if(lives == 0)
        {
            isAlive = false;
        }
    }

    public void update(Entity entity) {

    }
    public void render () {
    }
    public void movement() {
        if(isAlive)
        {
            Gdx.input.getX();
        }
    }
}
