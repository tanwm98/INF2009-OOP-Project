package com.mygdx.game;


public class Player {
    private int lives;
    private int score;
    private boolean isAlive = true;

    public Player() {
        this.lives = 3;
        this.score = 0;
    }

    public Player(int lives, int score) {
        this.lives = lives;
        this.score = score;
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