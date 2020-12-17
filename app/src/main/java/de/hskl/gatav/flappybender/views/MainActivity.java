package de.hskl.gatav.flappybender.views;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Player;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.sound.Discman;
import de.hskl.gatav.flappybender.sound.Music;

public class MainActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Activity
        setContentView(R.layout.activity_main);
        Game.getInstance().setContext(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameView gameview = findViewById(R.id.GAMEVIEW);
        gameview.stop();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        Game.getInstance().restart();
    }

    @Override
    protected String getMusic() {
        return Discman.MUSIC_GAME;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clicked(View view) {
        Game.getInstance().clickAction();
    }
}