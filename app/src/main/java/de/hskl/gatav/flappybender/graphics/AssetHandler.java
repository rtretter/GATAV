package de.hskl.gatav.flappybender.graphics;

import android.app.Activity;
import android.service.voice.VoiceInteractionSession;

import java.util.HashMap;
import java.util.Map;

import de.hskl.gatav.flappybender.R;

public class AssetHandler {

    private static AssetHandler INSTANCE;

    private final Activity activity;
    private final Map<String, Asset> assets;

    private AssetHandler(Activity activity) {
        this.activity = activity;
        this.assets = new HashMap<>();
    }

    private final void initAssets() {
        assets.put(Asset.ASSET_BENDER_PROF, Asset.loadFromId(R.drawable.bender_prof));
        assets.put(Asset.ASSET_OBSTACLE_TOP, Asset.loadFromId(R.drawable.obstacle_top));
        assets.put(Asset.ASSET_OBSTACLE_BOTTOM, Asset.loadFromId(R.drawable.obstacle_bottom));
        assets.put(Asset.ASSET_BACKGROUND_AGEB, Asset.loadFromId(R.drawable.background_level_ageb));
        assets.put(Asset.ASSET_BACKGROUND_R2D2, Asset.loadFromId(R.drawable.background_level_r2d2));
    }

    public Activity getActivity() {
        return activity;
    }

    public static Asset getAsset(String key) {
        return getInstance().assets.get(key);
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
