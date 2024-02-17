package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {
	private EntityManager entityManager;
	private Player player;
	private Entity ball,paddle;


	@Override
	public void create() {
		entityManager = new EntityManager();
		player = new Player();
		ball = new Ball(5,5,20,5,5);
		paddle = new Paddle(5,5,100);
		entityManager.addEntity(ball);
		entityManager.addEntity(paddle);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1, true);
		entityManager.renderEntities();
	}
	
	@Override
	public void dispose() {

	}
}
