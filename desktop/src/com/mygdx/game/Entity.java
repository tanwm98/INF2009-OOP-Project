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
	private int speed;
	private Texture tex;
	private Color color;
	private boolean isAIControl;	// Flag to determine whether entity is AI controlled.
	
	public Entity(Texture tex, int xPos, int yPos, boolean isAIControl) {
		setTexture(tex);
		setX(xPos);
		setY(yPos);
		setControl(isAIControl);
	}
	
	public Entity(Color color, int xPos, int yPos, boolean isAIControl) {
		setColor(color);
		setX(xPos);
		setY(yPos);
		setControl(isAIControl);
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
	
	// Control Getter and Setter
	public boolean getControl() {
		return isAIControl;
	}
	public void setControl(boolean isAIControl) {
		this.isAIControl = isAIControl;
	}
	
	
	// Speed Getter and Setter
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	
	// Color Getter and Setter
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	
	
	public abstract void update(Entity entity);
	public abstract void render(SpriteBatch batch);
	
}

