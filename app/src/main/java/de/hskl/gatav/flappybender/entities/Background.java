package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class Background extends Entity {

    private boolean createdNext;

    public Background(int xOff, int height) {
        this(xOff, (int) ((float) height / AssetHandler.getAsset(Asset.ASSET_BACKGROUND_AGEB).getHeight() * AssetHandler.getAsset(Asset.ASSET_BACKGROUND_AGEB).getWidth()), height);
    }

    public Background(int xOff, int width, int height) {
        super(xOff, 0, width, height, null);
        double rand = Math.random();
        if(rand < 0.5) {
            asset = AssetHandler.getAsset(Asset.ASSET_BACKGROUND_AGEB);
        } else {
            asset = AssetHandler.getAsset(Asset.ASSET_BACKGROUND_R2D2);
        }
        this.velX = -Obstacle.OBSTACLE_SPEED;
        this.zPos = -1;
    }

    @Override
    public void tick(Canvas canvas) {
        super.tick(canvas);
        if(!createdNext && x + width < canvas.getWidth()) {
            createdNext = true;
            EntityHandler.getInstance().addEntity(new Background((int) Math.ceil(x + width), height));
        }
        if(x + width < 0) {
            EntityHandler.getInstance().removeEntity(this);
        }
    }
}
