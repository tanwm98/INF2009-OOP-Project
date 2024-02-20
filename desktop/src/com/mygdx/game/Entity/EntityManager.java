package com.mygdx.game.Entity;

import java.util.*;

import com.mygdx.game.CollisionManager;

public class EntityManager{
    private List<Entity> entityList;
    private CollisionManager collisionManager;
    private boolean collide;

    public EntityManager(){
        entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void renderEntities() {
        for (Entity entity : entityList) {
            entity.render();
        }
    }
    public void removeEntity(Entity entity) {
    	entityList.remove(entity);
    }

    public void dispose() {
    	for (Entity entity : entityList) {
    		entity.dispose();
    	}
    }

	public void detect() {
		collisionManager = new CollisionManager();
		Entity ball = entityList.get(0);
		//Check for ball collision
		for(int i = 1; i<entityList.size(); i++) {
			collide = collisionManager.detectCollision(ball, entityList.get(i));
			ball.collide(collide);
		}
		//Check for block collision
		for(int i = 2; i<entityList.size(); i++) {
			collide = collisionManager.detectCollision(ball, entityList.get(i));
			if(collide) {
				entityList.remove(i);
			}
		}
	}

}