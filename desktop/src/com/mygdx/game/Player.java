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
    private MyGdxGame game;


    public Player(MyGdxGame game) {
        this.game = game;
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

    public void decreaseLives(int lives) {
        if (this.lives > 0) {
            this.lives -= lives;
            this.livesImage[this.lives] = null; // Remove the corresponding Image
        }
    }
    public void addScore(int points) {
        this.score += points;
    }
    public int getLives() {
        return this.lives;
    }
    public void setLives(int lives) {
    	this.lives = lives;
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
