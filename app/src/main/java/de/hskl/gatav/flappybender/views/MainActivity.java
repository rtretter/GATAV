package de.hskl.gatav.flappybender.views;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Player;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.sound.Discman;
import de.hskl.gatav.flappybender.sound.Music;

public class MainActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init Assets
        AssetHandler.createInstance(this);
        // Set Activity
        setContentView(R.layout.activity_main);
    }

    @Override
    protected String getMusic() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clicked(View view) {
        Player p = EntityHandler.getInstance().getPlayer();
        if(p == null) {
            return;
        }
        p.jump();
    }
}