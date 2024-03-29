package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class OutputManager {
	private Music backgroundMusic;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private float previousVolume = 1.0f; // Default volume is 1.0
	private Preferences preferences;
	private static OutputManager instance;
	
    public static OutputManager getInstance() {
        if (null == instance) {
            instance = new OutputManager();
        }
        return instance;
    }
	
	private OutputManager() {
		font = new BitmapFont();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gomarice_no_continue.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
		preferences = Gdx.app.getPreferences("MyGamePreferences");
		previousVolume = preferences.getFloat("volume", 1.0f);
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
		if(isSelected) {
			font.setColor(Color.YELLOW); 
		}
		else {
			font.setColor(Color.WHITE);
		}
		font.draw(batch,text,x,y);
	}
	
	public void setFontSize(int size) {
		parameter.size = size;
		font = generator.generateFont(parameter);
	}
	public BitmapFont getFont() {
		return font;
	}
	
	
	//control music (mute button in settings)
	public Music mute(boolean mute) {
		if(mute) {
			previousVolume = getVolume();
			setVolume(0);
		}
		else {
			backgroundMusic.setVolume(1);
			backgroundMusic.setVolume(previousVolume);
			setVolume(previousVolume);
		}
		return backgroundMusic;
	}
	
	public boolean isMuted() {
	    return preferences.getBoolean("isMuted", false); // Default to false if not found
	}
	
	public void setMuted(boolean muted) {
	    preferences.putBoolean("isMuted", muted);
	    preferences.flush(); 
	}
	
	// Method to retrieve the previous volume
    public float getPreviousVolume() {
        return previousVolume;
    }
	
	//Music selection to loop
	public Music musicStart(int music) {
		switch(music) {
		case 0:
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/menuBGM.mp3"));
			break;
		case 1: 
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/gameBGM.mp3"));
			break;
		case 2:
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/pauseBGM.mp3"));
			break;
		case 3:
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/minigameBGM.mp3"));
			break;
		case 4:
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bgm/GameOverBGM.mp3"));
			break;
		}
	    backgroundMusic.setVolume(getVolume());
	    backgroundMusic.setLooping(true);
	    backgroundMusic.play();
		return backgroundMusic;
	}
	
	public void setVolume(float volume) {
	    if (backgroundMusic != null) {
	        backgroundMusic.setVolume(volume);
	        Gdx.app.log("OutputManager", "Setting volume: " + volume);
	        
	        preferences.putFloat("volume", volume);
	        preferences.flush();
	    }
	}
	
	public float getVolume() {
	    return preferences.getFloat("volume", 1f);
	}

	//Music Stop
	public void soundEnd(Music backgroundMusic) { 
		backgroundMusic.stop();
	}
	public void  playsound(String sfx) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(sfx));
		sound.play(1.0f);
	}
	
	public void dispose() {
	    if (backgroundMusic != null) {
	        backgroundMusic.dispose();
	    }
	    if (generator != null) {
	        generator.dispose();
	    }
	    if (font != null) {
	        font.dispose();
	    }
	}
}
