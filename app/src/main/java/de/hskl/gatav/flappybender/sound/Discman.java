package de.hskl.gatav.flappybender.sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.util.Data;

public class Discman extends Service {

    private Map<String, Music> musicMap;

    public static String MUSIC_PRELOADER = "MUSIC_PRELOADER";
    public static String MUSIC_MENU = "MUSIC_MENU";
    public static String MUSIC_GAME = "MUSIC_GAME";

    private static final float MAX_VOLUME = 100;
    private static final int DEFAULT_VOLUME = 10;

    private static Discman INSTANCE;

    private MediaPlayer mediaPlayer;
    private int currentVolume = Discman.DEFAULT_VOLUME;

    private Music currentSong;

    public Discman() {
        INSTANCE = this;
        musicMap = new HashMap<>();
        musicMap.put(MUSIC_PRELOADER, new Music(R.raw.preloader, false));
        musicMap.put(MUSIC_MENU, new Music(R.raw.main_menu, true));
        musicMap.put(MUSIC_GAME, new Music(R.raw.background, true));
        currentSong = musicMap.get(MUSIC_PRELOADER);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setSong(currentSong);
    }

    public void setSong(Music music) {
        currentSong = music;
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, currentSong.getId());
        mediaPlayer.setLooping(music.loop());
        final float volume = currentVolume / MAX_VOLUME;
        System.out.println(volume);
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.start();
    }

    public void setSong(String song) {
        setSong(musicMap.get(song));
    }

    public void setVolume(int volumeNew) {
        currentVolume = volumeNew;
        final float volume = currentVolume / MAX_VOLUME;
        mediaPlayer.setVolume(volume, volume);
    }

    public void onPause() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void onResume() {
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public int getVolume() {
        return currentVolume;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        INSTANCE = null;
    }

    public static Discman getInstance() {
        return INSTANCE;
    }

    public static boolean wasCreated() {
        return INSTANCE != null;
    }
}
