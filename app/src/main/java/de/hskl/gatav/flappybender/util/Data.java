package de.hskl.gatav.flappybender.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.sound.Discman;

public class Data {

    public static final String dataFileName = "data.csv";
    private static boolean wasLoaded;

    public static void saveData(Context context) {
        File dataFile = new File(context.getFilesDir(), dataFileName);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile, false)));
            bw.write(Game.getInstance().getHighscore() + "," + Discman.getInstance().getVolume() + "," + Game.getInstance().getPlayerSkin().getResourceId());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Context context) {
        File dataFile = new File(context.getFilesDir(), dataFileName);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile)));
            String[] data = br.readLine().split(",");
            Game.getInstance().setHighscore(Integer.parseInt(data[0].trim()));
            Discman.getInstance().setVolume(Integer.parseInt(data[1].trim()));
            Game.getInstance().setPlayerSkin(AssetHandler.getAssetByResourceId(Integer.parseInt(data[2].trim())));
            br.close();
            wasLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean wasLoaded() {
        return wasLoaded;
    }

}
