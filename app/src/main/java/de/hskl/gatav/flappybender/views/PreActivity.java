package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.sound.Discman;

public class PreActivity extends OwnActivity {

    ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pre);

        start = findViewById(R.id.START_APP);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(start, PropertyValuesHolder.ofFloat("scaleX", 1.2f), PropertyValuesHolder.ofFloat("scaleY", 1.2f), PropertyValuesHolder.ofFloat("alpha", 1f));
        animator.setDuration(1000);

        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        // Init Assets
        AssetHandler.createInstance(this);

        start.setOnClickListener(this::startApp);
    }

    @Override
    protected String getMusic() {
        return Discman.MUSIC_PRELOADER;
    }

    public void startApp(View v){
        Intent intent = new Intent(PreActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, Discman.class);
        stopService(intent);
        finishAndRemoveTask();
    }
}