package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public abstract class Background extends Entity {

    private double paralaxFactor;
    private boolean createdNext;

    public Background(int xOff, int width, int height, Asset asset, double paralaxFactor, int zPos) {
        super(xOff, 0, width, height, asset);
        this.paralaxFactor = paralaxFactor;
        this.velX = -Obstacle.OBSTACLE_SPEED * paralaxFactor;
        this.zPos = zPos;
    }

    @Override
    public void tick(Canvas canvas) {
        super.tick(canvas);
        if(!createdNext && x + width < canvas.getWidth()) {
            createdNext = true;
            createNextBackground(canvas);
        }
        if(x + width < 0) {
            EntityHandler.getInstance().removeEntity(this);
        }
    }

    protected abstract void createNextBackground(Canvas canvas);
}
