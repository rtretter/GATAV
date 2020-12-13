package de.hskl.gatav.flappybender.graphics;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import de.hskl.gatav.flappybender.R;

public class Asset {

    public static Bitmap BMP_BENDER_PROF;

    public static void init(Activity activity) {
        BMP_BENDER_PROF = loadBitmap(R.drawable.bender_prof, activity);
    }

    private static Bitmap loadBitmap(int id, Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.bender_prof);
    }

}
