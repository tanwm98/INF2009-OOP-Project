package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Screens.MiniGameScreen;

public class Asteroid extends Entity {
	private Texture tex;
	private Player player;
	private SpriteBatch batch = new SpriteBatch();
	private MyGdxGame game;
	
    public Asteroid() {

    }
    
    public Asteroid(String filePath) {
    	super();
		tex = new Texture(Gdx.files.internal(filePath));
    }
	public Asteroid(String filePath, float posX, float posY,float speedX, float speedY,boolean Collideable,Player player, MyGdxGame game)
	{
		super.setX(posX);
		super.setY(posY);
		super.setXSpeed(speedX);
		super.setYSpeed(speedY);
		tex = new Texture(Gdx.files.internal(filePath));
		super.setCollideable(Collideable);
		super.setHeight(tex.getHeight());
		super.setWidth(tex.getWidth());
		this.player = player;
		this.game = game;
	}
	public Texture getTexture() {
		return tex;
	}
	
	public void setTexture(Texture t) {
		tex = t;
	}

    public void render() {
    	batch.begin();
    	OutputManager.getInstance().draw(batch, getTexture(), super.getX(), super.getY(), super.getWidth(), super.getHeight());
		batch.end();
    }

    public void dispose() {
    	batch.dispose();
    }

    public void move() {
		super.setX(super.getX() + super.getXSpeed());
		super.setY(super.getY() + super.getYSpeed());
		if(super.getX()  < 0 || super.getX()  > Gdx.graphics.getWidth())
		{
			super.setXSpeed(super.getXSpeed() * -1); //reverse x direction
		}
		if(super.getY()  < 0 || super.getY()  > Gdx.graphics.getHeight())
		{
			super.setYSpeed(super.getYSpeed() * -1); //reverse y direction
		}
    }
	public void update(float delta)
	{
		super.update(delta);
	}

    public void collide(boolean collide) {
		if(collide)
		{
			game.setScreen(new MiniGameScreen(game,player)); // Transition to the minigame screen
			OutputManager.getInstance().playsound("Music/sfx/hit_sfx.wav");
		}
    }

}
