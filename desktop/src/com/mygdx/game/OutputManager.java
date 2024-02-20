package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class OutputManager {
	private Music backgroundMusic;

	//Render
	public void draw(SpriteBatch batch,Texture tex,int x,int y)
	{
		batch.draw(tex,x,y,tex.getWidth(),tex.getHeight());
	}

//	public void draw(ShapeRenderer shape,Texture tex,int x,int y) {
//        shape.begin(ShapeRenderer.ShapeType.Filled);
//         {
//        	 shape.draw(tex,x,y,tex.getWidth(),tex.getHeight());
//        }
//        shape.end();
//	}

	//sound
	public void soundStart() {
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Toothless.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
	}

	public void soundEnd() {
		backgroundMusic.stop();
	}
}