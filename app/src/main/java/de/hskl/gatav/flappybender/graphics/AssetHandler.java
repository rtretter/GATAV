package de.hskl.gatav.flappybender.graphics;

import android.app.Activity;
import android.service.voice.VoiceInteractionSession;

import java.util.HashMap;
import java.util.Map;

import de.hskl.gatav.flappybender.R;

public class AssetHandler {

    private static AssetHandler INSTANCE;
    private static Asset LAST_BACKGROUND;

    private final Activity activity;
    private final Map<String, Asset> assets;

    private AssetHandler(Activity activity) {
        this.activity = activity;
        this.assets = new HashMap<>();
    }

    private final void initAssets() {
        assets.put(Asset.ASSET_BENDER_STANDARD, Asset.loadFromId(R.drawable.bender_roboter));
        assets.put(Asset.ASSET_BENDER_PROF, Asset.loadFromId(R.drawable.bender_prof));
        Asset obstacleBottom = Asset.loadFromId(R.drawable.obstacle_bottom);
        assets.put(Asset.ASSET_OBSTACLE_BOTTOM, obstacleBottom);
        assets.put(Asset.ASSET_OBSTACLE_TOP, obstacleBottom.getRotated(180));
        assets.put(Asset.ASSET_BACKGROUND_AGEB, Asset.loadFromId(R.drawable.background_level_ageb));
        assets.put(Asset.ASSET_BACKGROUND_R2D2, Asset.loadFromId(R.drawable.background_level_r2d2));
        assets.put(Asset.ASSET_BACKGROUND_SKY, Asset.loadFromId(R.drawable.background_sky));
        assets.put(Asset.ASSET_BACKGROUND_AUDIMAX, Asset.loadFromId(R.drawable.background_level_audimax));
    }

    public Activity getActivity() {
        return activity;
    }

    public static Asset getAsset(String key) {
        return getInstance().assets.get(key);
    }

    public static Asset getAssetByResourceId(int resourceId) {
        for(Asset asset: getInstance().assets.values()) {
            if(asset.getResourceId() == resourceId) {
                return asset;
            }
        }
        return Asset.loadFromId(resourceId);
    }

    public static Asset getRandomBackgroundAsset() {
        double rand = Math.random();
        Asset newBackground = null;
        while(newBackground == null) {
            if (rand < 0.33) {
                if(getLastBackground() != getAsset(Asset.ASSET_BACKGROUND_AGEB)) {
                    newBackground = getAsset(Asset.ASSET_BACKGROUND_AGEB);
                }
            } else if (rand < 0.66) {
                if(getLastBackground() != getAsset(Asset.ASSET_BACKGROUND_R2D2)) {
                    newBackground = getAsset(Asset.ASSET_BACKGROUND_R2D2);
                }
            } else {
                if(getLastBackground() != getAsset(Asset.ASSET_BACKGROUND_AUDIMAX)) {
                    newBackground = getAsset(Asset.ASSET_BACKGROUND_AUDIMAX);
                }
            }
        }
        return LAST_BACKGROUND = newBackground;
    }

    public static Asset getLastBackground() {
        return LAST_BACKGROUND;
    }

    public static AssetHandler getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("createInstance wasn't called!");
        }
        return INSTANCE;
    }

    public static void createInstance(Activity activity) {
        if (INSTANCE == null) {
            INSTANCE = new AssetHandler(activity);
            INSTANCE.initAssets();
        }
    }
}
