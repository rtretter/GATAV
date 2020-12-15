package de.hskl.gatav.flappybender.entities;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class Obstacle extends Entity {

    public static final int OBSTACLE_TOP = 1;
    public static final int OBSTACLE_BOTTOM = 0;
    public static final double OBSTACLE_SPEED = 7;
    private static final int OBSTACLE_WIDTH = 150;

    private boolean playerPassed = false;

    private int type;

    public Obstacle(int x, int y, int height, int obstacleType) {
        super(x, y, OBSTACLE_WIDTH, height, (obstacleType == OBSTACLE_TOP) ? AssetHandler.getAsset(Asset.ASSET_OBSTACLE_TOP) : AssetHandler.getAsset(Asset.ASSET_OBSTACLE_BOTTOM));
        this.type = obstacleType;
        this.velX = -OBSTACLE_SPEED;
    }

    public boolean playedPassed() {
        if(type != OBSTACLE_TOP) {
            return false;
        }
        if(!playerPassed) {
            playerPassed = true;
            return true;
        }
        return false;
    }
}
