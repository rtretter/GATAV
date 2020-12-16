package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;

public class MenuActivity extends OwnActivity {

    Button start;
    Button highscore;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if(Discman.wasCreated()) {
            Discman.getInstance().setSong(Discman.MUSIC_MENU);
        }

        start = findViewById(R.id.BUTTON_PLAY);;
        highscore = findViewById(R.id.BUTTON_HIGHSCORE);
        options = findViewById(R.id.BUTTON_OPTIONS);

        start.setOnClickListener(this::startGame);
        options.setOnClickListener(this::options);

    }

    private void options(View view) {
        Intent intent = new Intent(MenuActivity.this, OptionsActivity.class);
        startActivity(intent);
    }

    private void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}