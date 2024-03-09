package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class OutputManager {
	private Music backgroundMusic;
	private BitmapFont font;
	//private List<Screen> screenList;
	
	public OutputManager() {
		//screenList = new ArrayList<>();
		font = new BitmapFont(); 
    }
	
	//Draw Render
	public void draw(SpriteBatch batch,Texture tex, int x, int y) {
		batch.draw(tex,x,y,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void draw(SpriteBatch batch, String text, float x, float y) {
		font.draw(batch,text,x,y);
	}
	
	public void draw(SpriteBatch batch, GlyphLayout text, float x, float y) {
		font.draw(batch,text,x,y);
	}
	
	//Selection of text
	public void draw(SpriteBatch batch, String text, float x, float y, boolean isSelected) {
		if(isSelected) {
			font.setColor(Color.YELLOW); 
		}
		else {
			font.setColor(Color.WHITE);
		}
		font.draw(batch,text,x,y);
	}
	
	//Circle Entity
	public void draw(ShapeRenderer shape, float x,float y,float size) {
		shape.circle(x,y,size);
	}
	
	//Block Entity
	public void draw(ShapeRenderer shape, float x,float y,float width,float height) {
		shape.rect(x,y,width,height);
	}
	
	//Music start
	public Music musicStart(boolean music) {
		if(music) {
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Toothless.mp3"));
		}
		else {
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Wii.mp3"));
		}
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		return backgroundMusic;
	}
	
	//Music Stop
	public void soundEnd(Music backgroundMusic) { 
		backgroundMusic.stop();
	}
	
	
	/*public void draw(SpriteBatch batch) {
		batch.begin();
		for (Screen screen : screenList) {
			screen.draw(batch);
	    }
	    batch.end();
		}
	
	public void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Screen screen : screenList) {
	       screen.draw(shape);
		}
		shape.end();
		}
	
	//start music
	public void show() {
		for (Screen screen : screenList) {
			screen.show();
			}
		}
		
	//stop music
	public void hide() {
		for (Screen screen : screenList) {
			screen.hide();
			}
	}*/
}
