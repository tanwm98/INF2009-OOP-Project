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

	public boolean isAKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.A);
	}
	public boolean isSKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.S);
	}
	public boolean isDKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.D);
	}
	public boolean isWKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.W);
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
