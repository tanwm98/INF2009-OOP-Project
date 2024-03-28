package com.mygdx.game.Managers;

import java.util.*;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Entity.Asteroid;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Planet;
import com.mygdx.game.Entity.Satellite;
import com.mygdx.game.Interfaces.ICollideable;

public class EntityManager extends MyGdxGame{
    private List<Entity> entityList;
    private List<ICollideable> CollideableObjects;
    private CollisionManager collisionManager;
    private AIControlManager aiControlManager;
    private OutputManager outputManager;
    private PlayerControlManager playerControlManager;
    private boolean collide;

    public EntityManager(){
        entityList = new ArrayList<>();
    }
    public EntityManager(AIControlManager aiControlManager, CollisionManager collisionManager,OutputManager outputManager,PlayerControlManager playerControlManager)
    //uses the same AIControlManager as the game
    {
        this.entityList = new ArrayList<>();
        this.aiControlManager = aiControlManager;
        this.collisionManager = collisionManager;
        this.playerControlManager = playerControlManager;
        this.outputManager = outputManager;
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
        if(entity.isCollideable()) {
        	collisionManager.addEntity(entity);
        }
    }

    public void updateEntities(float delta) {
        for (Entity entity : entityList) {
            entity.update(delta);
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
    public List<Entity> getEntities() {
        return entityList;
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
        else {
        	CollideableObjects = collisionManager.getCollideables();
        	ICollideable player = CollideableObjects.get(0);
        	for(int i = 1; i<CollideableObjects.size(); i++){
        		ICollideable object = CollideableObjects.get(i);
        		collide = collisionManager.detectCollision(player, object);
        		if(collide) {
        			if(object instanceof Asteroid || object instanceof Satellite) {
        				player.collide(collide);
        			}

        			object.collide(collide);
        		}

        	}
        }
	}
}
