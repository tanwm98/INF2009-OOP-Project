package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class OutputManager {
	private Music backgroundMusic;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	
	public OutputManager() {
		font = new BitmapFont();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gomarice_no_continue.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
    }
	//Draw Render
	public void draw(SpriteBatch batch,Texture tex, float x, float y) {
		batch.draw(tex,x,y,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	// Draw Render for textures, adjustable size and no rotation.
	public void draw(SpriteBatch batch,Texture tex, float x, float y, float sizeX, float sizeY) {
		batch.draw(tex,x,y,sizeX,sizeY);
	}
	
	// Draw Render for textures that Rotate.
	// Origin X and Y = (this.getTexture().getWidth or Height())/2
	// srcWidth and Height are original texture dimensions.
	public void draw(SpriteBatch batch, Texture tex, float x, float y, float originX, float originY, float texWidth, float texHeight, float scalingFactor, float rotation) {
		batch.draw(tex, x, y,
				originX, originY, texWidth, texHeight,
				scalingFactor, scalingFactor, rotation,
				0, 0, (int)texWidth, (int)texHeight,
				false, false);
	}

	
	public void draw(SpriteBatch batch, String text, float x, float y) {
		font.setColor(Color.WHITE);
		font.draw(batch,text,x,y);
	}
	
	public void draw(SpriteBatch batch, GlyphLayout text, float x, float y) {
		font.draw(batch,text,x,y);
	}
	
	//Selection of text
	public void draw(SpriteBatch batch, String text, float x, float y, boolean isSelected) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		GlyphLayout layout = new GlyphLayout(font, text);

		if(isSelected) {
			font.setColor(Color.YELLOW); 
		}
		else {
			font.setColor(Color.WHITE);
		}
		font.draw(batch,text,x,y);
	}
	
	//control music (mute button in settings)
	public Music mute(boolean music) {
		if(music) {
			backgroundMusic.setVolume(0);
		}
		else {
			backgroundMusic.setVolume(1);
		}
		return backgroundMusic;
	}
	
	//Music start
	public Music musicStart(boolean music,float volume) {
		if(music) {
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/menuBGM.mp3"));
		}
		else {
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/gameBGM.mp3"));
		}
		setVolume(volume);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		return backgroundMusic;
	}
	
	public void setVolume(float volume) {
	    if (backgroundMusic != null) {
	        backgroundMusic.setVolume(volume);
	    }
	}
	
	public float getVolume() {
	    if (backgroundMusic != null) {
	        return backgroundMusic.getVolume();
	    }
		return 1;
	}

	public void setFontSize(int size) {
		parameter.size = size;
		font = generator.generateFont(parameter);
	}
	//Music Stop
	public void soundEnd(Music backgroundMusic) { 
		backgroundMusic.stop();
	}
	public BitmapFont getFont() {
		return font;
	}
	public void  playsound(String sfx) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(sfx));
		sound.play(1.0f);

	}
}
