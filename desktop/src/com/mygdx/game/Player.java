package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player{

    private int lives,score;

    private OrthographicCamera camera; //won't be used for the demo
    public Player(){
        lives = 3;
        score = 0;
    }
    public Player(int lives)
    {
        if(lives <= 0)
        {
            throw new IllegalArgumentException("Lives cannot be negative or zero");
        }
        this.lives = lives;
        score = 0;
    }
    public Player(int lives, int score){
        if(lives < 0 || score < 0)
        {
            throw new IllegalArgumentException("Lives and score cannot be negative");
        }
        this.lives = lives;
        this.score = score;
    }
    public void addScore(int points)
    {
        score += points;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
