package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Player getPlayer(){
        return (Player) entities.stream().filter((e) -> e instanceof Player).findFirst().get();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Entity> getObstacles() {
        return entities.stream().filter((e) -> e instanceof Obstacle).collect(Collectors.toList());
    }

}
