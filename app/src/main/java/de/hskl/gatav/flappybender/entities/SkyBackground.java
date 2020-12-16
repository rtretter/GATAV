package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class SkyBackground extends Background {

    private static final double PARALAX_FACTOR = 0.5;

    public SkyBackground(int xOff, int height) {
        super(xOff, (int) ((float) height / AssetHandler.getAsset(Asset.ASSET_BACKGROUND_SKY).getHeight() * AssetHandler.getAsset(Asset.ASSET_BACKGROUND_SKY).getWidth()), height, AssetHandler.getAsset(Asset.ASSET_BACKGROUND_SKY), PARALAX_FACTOR, -2);
    }

    @Override
    protected void createNextBackground(Canvas canvas) {
        EntityHandler.getInstance().addEntity(new SkyBackground((int) Math.ceil(x + width + velX), height));
    }
}
