package de.hskl.gatav.flappybender.logic;

import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.logging.Level;

import de.hskl.gatav.flappybender.entities.Entity;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Obstacle;

public class LevelGenerator {

    private static LevelGenerator INSTANCE;
    private static final double TICK_GENERATE_FACTOR = 0.8;
    private static final int GAP = 550;
    private static final int MINIMUM_HEIGHT = 100;
    private int tickCounter;
    private boolean spawning;

    private LevelGenerator() {
    }

    public void restart() {
        tickCounter = 0;
        spawning = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void tick(Canvas canvas) {
        if(!spawning) {
            return;
        }
        tickCounter++;

        if (tickCounter >= canvas.getWidth() / Obstacle.OBSTACLE_SPEED * TICK_GENERATE_FACTOR) {
            int height = MINIMUM_HEIGHT + (int) (Math.random() * (canvas.getHeight() - GAP - MINIMUM_HEIGHT * 2));
            EntityHandler.getInstance().addEntity(new Obstacle(canvas.getWidth(), 0, height, Obstacle.OBSTACLE_TOP));
            EntityHandler.getInstance().addEntity(new Obstacle(canvas.getWidth(), height + GAP, canvas.getHeight() - (height + GAP), Obstacle.OBSTACLE_BOTTOM));
            tickCounter = 0;
        }

        List<Entity> obstacles = EntityHandler.getInstance().getObstacles();

        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getX() < -obstacles.get(i).getWidth()) {
                EntityHandler.getInstance().removeEntity(obstacles.get(i));
            }
        }
    }

    public void startSpawning() {
        spawning = true;
    }

    public static LevelGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LevelGenerator();
        }
        return INSTANCE;
    }
}
