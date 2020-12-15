package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityHandler {

    private static EntityHandler INSTANCE;

    private final List<Entity> entities, entitiesToAdd, entitiesToRemove;

    private EntityHandler() {
        entitiesToAdd = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
        entities = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void tick(Canvas canvas) {
        if(!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entities.sort((e1, e2) -> Integer.compare(e1.zPos, e2.zPos));
            entitiesToAdd.clear();
        }
        if(!entitiesToRemove.isEmpty()) {
            entities.removeAll(entitiesToRemove);
            entitiesToRemove.clear();
        }
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).tick(canvas);
        }
    }

    public void render(Canvas canvas) {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(canvas);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Player getPlayer(){
        return (Player) entities.stream().filter((e) -> e instanceof Player).findFirst().get();
    }

    public void addEntity(Entity e) {
        entitiesToAdd.add(e);
    }

    public void removeEntity(Entity e) {
        entitiesToRemove.add(e);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Entity> getObstacles() {
        return entities.stream().filter((e) -> e instanceof Obstacle).collect(Collectors.toList());
    }

}
