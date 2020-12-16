package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;
import de.hskl.gatav.flappybender.sound.Music;

public class MenuActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button start = findViewById(R.id.BUTTON_PLAY);;
        Button highscore = findViewById(R.id.BUTTON_HIGHSCORE);
        Button skins = findViewById(R.id.BUTTON_SKINS);
        Button options = findViewById(R.id.BUTTON_OPTIONS);
        Button exit = findViewById(R.id.BUTTON_EXIT);

        start.setOnClickListener(this::startGame);
        options.setOnClickListener(this::options);
        exit.setOnClickListener(this::exit);

    }

    @Override
    protected String getMusic() {
        return Discman.MUSIC_MENU;
    }

    private void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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