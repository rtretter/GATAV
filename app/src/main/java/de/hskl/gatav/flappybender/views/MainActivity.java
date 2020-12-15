package de.hskl.gatav.flappybender.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.sound.Discman;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init Assets
        AssetHandler.createInstance(this);
        // Make fullscreen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().hide();
        // Set Activity
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, Discman.class);
        startService(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clicked(View view) {
        EntityHandler.getInstance().getPlayer().jump();
    }
}