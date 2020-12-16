package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class LevelBackground extends Background {

    private static final double PARALAX_FACTOR = 0.75;

    public LevelBackground(int xOff, int height) {
        super(xOff, (int) ((float) height / AssetHandler.getRandomBackgroundAsset().getHeight() * AssetHandler.getLastBackground().getWidth()), height, AssetHandler.getLastBackground(), PARALAX_FACTOR, -1);
    }

    @Override
    protected void createNextBackground(Canvas canvas) {
        EntityHandler.getInstance().addEntity(new LevelBackground((int) Math.ceil(x + width + velX), height));
    }
}
