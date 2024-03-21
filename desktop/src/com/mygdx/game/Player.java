package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player {
    private static Player instance = null;
    private int lives = 3;
    private int score = 0;
    private boolean isAlive = true;
    private OrthographicCamera camera;

    private Player() {
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void death() {
        --this.lives;
        if (this.lives == 0) {
            this.isAlive = false;
        }
    }

    public void addScore(int points) {
        this.score += points;
    }
}