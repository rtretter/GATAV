package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EntityHandler {

    private static EntityHandler INSTANCE;

    private final List<Entity> entities;

    private EntityHandler() {
        entities = new ArrayList<>();
    }

    public void tick(Canvas canvas) {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).tick(canvas);
        }
    }

    public void render(Canvas canvas) {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(canvas);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void clear() {
        entities.clear();
    }

    public static EntityHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EntityHandler();
        }
        return INSTANCE;
    }

}
