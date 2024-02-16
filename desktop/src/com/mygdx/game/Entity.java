package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;


public abstract class Entity extends EntityManager implements ICollideable{
	private int xPos;
	private int yPos;
	private Texture tex;
	private int speed;
	private boolean aiControl;	// Flag to determine whether entity is AI controlled.
	
	// X position Getter and Setter
	public int getX() {
		return xPos;
	}
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	
	// Y position Getter and Setter
	public int getY() {
		return yPos;
	}
	public void setY(int yPos) {
		this.yPos = yPos;
	}
	
	// Texture Getter and Setter
	public void setTexture(Texture tex) {
		this.tex = tex;
	}
	public Texture getTexture() {
		return tex;
	}
	
	// Speed Getter and Setter
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	
	
	public abstract void update(Entity entity);
	public abstract void render(SpriteBatch batch);
	
}

