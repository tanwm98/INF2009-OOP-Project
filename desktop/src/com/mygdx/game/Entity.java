package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity extends EntityManager {
	private int xPos;
	private int yPos;
	private Texture tex;
	
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
	
	
	public abstract void update(Entity entity);
	public abstract void render(SpriteBatch batch);
	
}