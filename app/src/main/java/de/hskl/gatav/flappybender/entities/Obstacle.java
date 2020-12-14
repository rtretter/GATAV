package de.hskl.gatav.flappybender.entities;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class Obstacle extends Entity{

    public static final int OBSTACLE_TOP=1;
    public static final int OBSTACLE_BOTTOM=0;
    private static final int OBSTACLE_WIDTH=150;

    public Obstacle(int x, int y, int height, int obstacleType) {
        super(x, y, OBSTACLE_WIDTH, height, (obstacleType==OBSTACLE_TOP)?AssetHandler.getAsset(Asset.ASSET_OBSTACLE_TOP):AssetHandler.getAsset(Asset.ASSET_OBSTACLE_BOTTOM));
        velX=-5;
    }
}
