package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Paddle;
import com.mygdx.game.Interfaces.IMovement;

public class InputManager {
	public boolean isLeftKeyPressed() {
		return Gdx.input.isKeyPressed(Keys.LEFT);
	}

	public boolean isRightKeyPressed() {
		return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
	}
}