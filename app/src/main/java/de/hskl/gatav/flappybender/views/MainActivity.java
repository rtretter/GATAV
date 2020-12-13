package de.hskl.gatav.flappybender.views;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init Assets
        AssetHandler.createInstance(this);
        // Make fullscreen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        // Set Activity
        setContentView(R.layout.activity_main);
    }
}