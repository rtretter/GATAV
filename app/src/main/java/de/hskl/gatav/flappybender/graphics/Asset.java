package de.hskl.gatav.flappybender.graphics;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import de.hskl.gatav.flappybender.R;

public class Asset {

    public static final String ASSET_BENDER_PROF = "BENDER_PROF";
    public static final String ASSET_OBSTACLE_TOP = "OBSTACLE_TOP";
    public static final String ASSET_OBSTACLE_BOTTOM = "OBSTACLE_BOTTOM";
    public static final String ASSET_BACKGROUND_AGEB = "BACKGROUND_AGEB";
    public static final String ASSET_BACKGROUND_R2D2 = "BACKGROUND_R2D2";

    private final int resourceId;
    private Bitmap bitmap;

    private Asset(Bitmap bitmap, int resourceId) {
        this.resourceId = resourceId;
        this.bitmap = bitmap;
    }

    public void render(Canvas canvas, double x, double y, int width, int height) {
        canvas.drawBitmap(bitmap, null, new RectF((float) x, (float) y, (float) (x + width), (float) (y + height)), null);
    }

    public void render(Canvas canvas, double x, double y, int width) {
        canvas.drawBitmap(bitmap, null, new RectF((float) x, (float) y, (float) (x + width), (float) (y + (float) width / bitmap.getWidth() * bitmap.getHeight())), null);
    }

    public static Asset loadFromId(int id) {
        return new Asset(BitmapFactory.decodeResource(AssetHandler.getInstance().getActivity().getResources(), id), id);
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

}
