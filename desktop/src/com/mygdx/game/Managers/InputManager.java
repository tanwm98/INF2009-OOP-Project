package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputManager {
	//KeyPressed
	public boolean isLeftKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.LEFT);
	}
	public boolean isRightKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.RIGHT);
	}
	public boolean isUpKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.UP);
	}
	public boolean isDownKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.DOWN);
	}
	public boolean isEscapeKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.ESCAPE);
	}
	
	//KeyJustPressed
	public boolean isLeftKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.LEFT);
	}
	public boolean isRightKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.RIGHT);
	}
	public boolean isUpKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.UP);
	}
	public boolean isDownKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.DOWN);
	}
	public boolean isEnterKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.ENTER);
	}
}
