package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class InputManager {
	private float mouseX;
	private float mouseY;
		
	//keyboard input
	public void keyboard(Paddle p){
		if(Gdx.input.isKeyPressed(Keys.A)) {
			p.moveLeft();
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			p.moveRight();
		}
	}
	
    public boolean isLMBPressed(int input){
        if (mousePressed() && input == Buttons.LEFT) {
        	System.out.println("LEFT LEFT LEFT");
        	return true;
        }
        else{
        	return false;
        }
    }

    public boolean isRMBPressed(int input){
        if (mousePressed() && input == Buttons.RIGHT) {
        	return true;
        }
        else{
        	return false;
        }
    }
    
    public boolean mousePressed() {
    	return true;
    }
    
    
    
	/*public void mouse(){
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			mouseX = Gdx.input.getX();
            mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
			
		}
	}*/
}