package com.mygdx.game.Entity;

import java.util.*;

import com.mygdx.game.CollisionManager;
import com.mygdx.game.AIControlManager;

public class EntityManager{
    private List<Entity> entityList;
    private CollisionManager collisionManager;
    private AIControlManager aiControlManager;
    private boolean collide;

    public EntityManager(){
        entityList = new ArrayList<>();
        aiControlManager = new AIControlManager();
    }
    public EntityManager(AIControlManager aiControlManager)
    //uses the same AIControlManager as the game
    {
        this.entityList = new ArrayList<>();
        this.aiControlManager = aiControlManager;
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
        if (entity.isAIControl()) {
            aiControlManager.addEntity(entity); //adds entity with AI movement to the list
        }
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
        if (entityList == null) {
            throw new NullPointerException("Entity list is null");
        }
        if (entityList.isEmpty()) {
            throw new IndexOutOfBoundsException("Entity list is empty");
        }
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