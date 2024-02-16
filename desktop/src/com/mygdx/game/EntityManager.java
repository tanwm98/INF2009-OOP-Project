package com.mygdx.game;

import java.util.*;
import com.badlogic.gdx.graphics.Color;

public class EntityManager{
    private List<Entity> entityList;

    public EntityManager()
    {
        entityList = new ArrayList<>();
//        entityList = new ArrayList<>();
//        entityList.add(new TextureObject("droplet.png", new float[]{200.0f,200.0f}, 2, true));
//        entityList.add(new TextureObject("bucket.png", new float[]{200.0f,0.0f},false));
//        entityList.add(new Circle(new float[]{500.0f,200.0f},50, Color.RED));
//        entityList.add(new Triangle(new float[]{0.0f,10.0f,170.0f,10.0f,100.0f,150.0f},
//                new Color[]{Color.GREEN,Color.GREEN,Color.GREEN},10));
    }

    public void createEntity(Entity entity) {
        entityList.add(entity);
    }
    public void manageEntity(Entity entity) {

    }
    public void updateEntity()
    {
        for(Entity entity: entityList)
        {
            entity.update();
        }
    }

}
