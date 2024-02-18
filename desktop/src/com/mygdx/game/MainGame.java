package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MainGame extends ApplicationAdapter {
	private EntityManager entityManager;
	private Player player;
	private Entity ball;
	private Entity paddle;



	@Override
	public void create() {
		entityManager = new EntityManager();
		player = new Player();
		ball = new Ball(5,5,50,5,5,true);
		paddle = new Paddle(100,50,100,100,20,false);
		entityManager.addEntity(ball);
		entityManager.addEntity(paddle);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
		entityManager.renderEntities();
		entityManager.moveEntities();
	}
	
	@Override
	public void dispose() {

	}
}
