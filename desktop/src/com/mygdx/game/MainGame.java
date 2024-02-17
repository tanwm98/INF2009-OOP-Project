package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class MainGame extends ApplicationAdapter {
	private Entity player;
	private EntityManager entityManager;
	@Override
	public void create () {
		entityManager = new EntityManager();
		player = new Player();
	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {
	}
}
