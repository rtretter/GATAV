package de.hskl.gatav.flappybender.util;

import android.util.Log;

public class Debug {
    public static void timeMethod(Runnable r){

        long timeR1 = System.nanoTime();
        r.run();
        long timeR2 = System.nanoTime();
        Log.e("TIMEOUTPUT",String.valueOf((timeR2-timeR1)/1000000.0));

    }
}
