package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Managers.OutputManager;

public class Player {
    private int lives;
    private int score;
    private boolean isAlive = true;
    private Image[] livesImage;
    private Texture tex;
    private SpriteBatch batch = new SpriteBatch();
    OutputManager outputManager;


    public Player() {
        this.lives = 3;
        this.score = 0;
        this.tex = new Texture("Objects/Player/lives01.png");
        this.livesImage = new Image[lives];
        for (int i = 0; i < lives; i++) {
            livesImage[i] = new Image(tex);
        }
        batch = new SpriteBatch();
        outputManager = new OutputManager();
    }

    public Player(int lives, int score) {
        this.lives = lives;
        this.score = score;
        this.tex = new Texture("Objects/Player/lives01.png");
        this.livesImage = new Image[lives];
        for (int i = 0; i < lives; i++) {
            livesImage[i] = new Image(tex);
        }
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
    public int getLives() {
        return this.lives;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
    public void drawPlayer()
    {
        int spacing = 50;
        for (int i = 0; i < lives; i++) {
            batch.begin();
            batch.draw(tex, i*spacing, 0, 50, 50);
            batch.end();
        }
    }

    public void drawScore()
    {
        batch.begin();
        outputManager.draw(batch, "Score: " + getScore(), 0, 100);
        batch.end();
    }
}