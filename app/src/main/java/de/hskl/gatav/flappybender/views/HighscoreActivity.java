package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.sound.Discman;

public class HighscoreActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Button back = findViewById(R.id.HS_BACK);
        back.setOnClickListener(this::back);

        TextView myHighscore = findViewById(R.id.HS_SC_N);
        TextView myGlobalPlace = findViewById(R.id.HS_SC_NG);

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

    private void back(View v){
        Intent intent = new Intent(HighscoreActivity.this, MenuActivity.class);
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