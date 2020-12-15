package de.hskl.gatav.flappybender.views;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;

public class PreActivity extends AppCompatActivity {

    ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pre);

        Intent intent = new Intent(PreActivity.this, Discman.class);
        startService(intent);


        start = findViewById(R.id.START_APP);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(start, PropertyValuesHolder.ofFloat("scaleX", 1.2f), PropertyValuesHolder.ofFloat("scaleY", 1.2f), PropertyValuesHolder.ofFloat("alpha", 1f));
        animator.setDuration(1000);

        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        start.setOnClickListener(this::startApp);
    }

    public void startApp(View v){
        Intent intent = new Intent(PreActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Discman.wasCreated()) {
            Discman.getInstance().onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Discman.wasCreated()) {
            Discman.getInstance().onResume();
        }
    }
}