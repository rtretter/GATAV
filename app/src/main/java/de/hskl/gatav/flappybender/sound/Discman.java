package de.hskl.gatav.flappybender.sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import de.hskl.gatav.flappybender.R;

public class Discman extends Service {

    private static Discman INSTANCE;

    private MediaPlayer mediaPlayer;
    private int currentSong;

    public Discman() {
        INSTANCE = this;
        currentSong = R.raw.background;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, currentSong);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();

    }

    public void setSong(int song) {
        currentSong = song;
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this, currentSong);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();
    }

    public void onPause() {
        mediaPlayer.pause();
    }

    public void onResume() {
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public static Discman getInstance() {
        return INSTANCE;
    }

    public static boolean wasCreated() {
        return INSTANCE != null;
    }
}
