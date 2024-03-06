package com.mygdx.game.Managers;


import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Interfaces.IMovement;

import java.util.ArrayList;
import java.util.List;

public class AIControlManager {
    private List<IMovement> aiMovements;
    public AIControlManager()
    {
        aiMovements = new ArrayList<>();
    }
    public void addEntity(Entity entity) {
        if(entity.isAIControl()) // If the entity is AI controlled, add it to the list
        {
                aiMovements.add(entity);
        }
    }

    public void moveEntities()
    {
        for (IMovement entity : aiMovements) {
            entity.move();
        }
    }
}
