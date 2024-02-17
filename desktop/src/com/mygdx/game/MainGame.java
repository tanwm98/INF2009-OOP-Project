package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class MainGame extends ApplicationAdapter {
	private EntityManager entityManager;
	private Entity ball,paddle,player;
	@Override
	public void create () {
		entityManager = new EntityManager();
		player = new Player();
		ball = new Ball();
		paddle = new Paddle();
		entityManager.addEntity(player);
		entityManager.addEntity(ball);
		entityManager.addEntity(paddle);
	}

	@Override
	public void render () {
		entityManager.renderEntities();
	}
	
	@Override
	public void dispose () {
	}
}
