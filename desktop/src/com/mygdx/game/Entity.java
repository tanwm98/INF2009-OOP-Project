package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public abstract class Entity extends EntityManager implements ICollideable{
	private float xPos;
	private float yPos;
	private int speed;
	private boolean isAIControl;	// Flag to determine whether entity is AI controlled.
	
	public Entity(int speed, float xPos, float yPos, boolean isAIControl) {
		setSpeed(speed);
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
	
	
	
	public abstract void update(Entity entity);
	public abstract void render(SpriteBatch batch);
	
}

