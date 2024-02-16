package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;


public abstract class Entity extends EntityManager implements ICollideable{
	private float xPos;
	private float yPos;
	private Texture tex;
	private boolean aiControl;	// Flag to determine whether entity is AI controlled.
	
	public Entity(Texture tex, int xPos, int yPos, boolean aiControl) {
		setTexture(tex);
		setX(xPos);
		setY(yPos);
		setControl(aiControl);
	}
	
	// X position Getter and Setter
	public float getX() {
		return xPos;
	}
	public void setX(float xPos) {
		this.xPos = xPos;
	}
	
	// Y position Getter and Setter
	public float getY() {
		return yPos;
	}
	public void setY(float yPos) {
		this.yPos = yPos;
	}
	
	// Texture Getter and Setter
	public void setTexture(Texture tex) {
		this.tex = tex;
	}
	public Texture getTexture() {
		return tex;
	}
	
	public boolean getControl() {
		return aiControl;
	}
	public void setControl(boolean aiControl) {
		this.aiControl = aiControl;
	}
	
	
	public abstract void update(Entity entity);
	public abstract void render(SpriteBatch batch);
	
}

