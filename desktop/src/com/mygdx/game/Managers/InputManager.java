package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.*;

public class InputManager {
	private static InputManager instance;

    public static InputManager getInstance() {
        if (null == instance) {
            instance = new InputManager();
        }
        return instance;
    }
	private InputManager(){
		//Private Constructor to prevent instantiation
	}
	
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
	public boolean isEscapeKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.ESCAPE);
	}
	
	public boolean isAKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.A);
	}
	public boolean isSKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.S);
	}
	public boolean isDKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.D);
	}
	public boolean isWKeyJustPressed() {
		return Gdx.input.isKeyJustPressed(Keys.W);
	}
	
	//Mouse
	public boolean leftClick () {
		return Gdx.input.isButtonJustPressed(Buttons.LEFT);
	}
	public boolean rightClick () {
		return Gdx.input.isButtonJustPressed(Buttons.RIGHT);
	}
	
	public void dispose() {
		
	}
}
