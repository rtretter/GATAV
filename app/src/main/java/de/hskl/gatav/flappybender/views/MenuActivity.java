package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;

public class MenuActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button start = findViewById(R.id.BUTTON_PLAY);
        Button skins = findViewById(R.id.BUTTON_SKINS);
        Button options = findViewById(R.id.BUTTON_OPTIONS);
        Button exit = findViewById(R.id.BUTTON_EXIT);

        start.setOnClickListener(this::startGame);
        options.setOnClickListener(this::options);
        exit.setOnClickListener(this::exit);
        skins.setOnClickListener(this::chooseSkins);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.LOGO_PRE_MENU), PropertyValuesHolder.ofFloat("scaleX", 0.95f), PropertyValuesHolder.ofFloat("scaleY", 0.95f), PropertyValuesHolder.ofFloat("rotation", -2));
        animator.setDuration(500);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

    }

    @Override
    protected String getMusic() {
        return Discman.MUSIC_MENU;
    }

    private void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void chooseSkins(View v){
        Intent intent = new Intent(MenuActivity.this, SkinActivity.class);
        startActivity(intent);
    }

    private void options(View view) {
        Intent intent = new Intent(MenuActivity.this, OptionsActivity.class);
        startActivity(intent);
    }

    private void exit(View view) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        exit(null);
    }
}