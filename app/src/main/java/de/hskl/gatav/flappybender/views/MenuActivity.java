package de.hskl.gatav.flappybender.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;

public class MenuActivity extends AppCompatActivity {

    Button start;
    Button highscore;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().hide();
        Discman.getInstance().setSong(R.raw.main_menu);

        start = findViewById(R.id.BUTTON_PLAY);;
        highscore = findViewById(R.id.BUTTON_HIGHSCORE);
        options = findViewById(R.id.BUTTON_OPTIONS);

        start.setOnClickListener(this::startGame);

    }

    public void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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