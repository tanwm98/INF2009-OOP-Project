package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {

	ShapeRenderer shape;
	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(50,50,50);
		shape.end();
	}
	
	@Override
	public void dispose () {
		shape.dispose();
	}
}
