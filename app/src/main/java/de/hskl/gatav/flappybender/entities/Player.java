package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class Player extends Entity {

    public Player(int x, int y, int width) {
        this(x, y, width, 0);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, AssetHandler.getAsset(Asset.ASSET_BENDER_PROF));
    }
}
