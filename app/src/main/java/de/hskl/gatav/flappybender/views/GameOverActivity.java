package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.util.Data;

public class GameOverActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button again = findViewById(R.id.GO_AGAIN);
        Button main = findViewById(R.id.GO_MAIN);
        TextView currentScore = findViewById(R.id.HIGHSCORE);
        TextView myHighscore = findViewById(R.id.PHIGHS);


        currentScore.setText(String.valueOf(Game.getInstance().getScore()));
        myHighscore.setText(String.valueOf(Game.getInstance().getHighscore()));

        again.setOnClickListener(this::startGame);
        main.setOnClickListener(this::back);

        TextView congratulations = findViewById(R.id.TEXT_CONGRATULATIONS);

        if(Game.getInstance().wasNewHighscore()) {
            congratulations.setVisibility(TextView.VISIBLE);
            ObjectAnimator animatorCongratz = ObjectAnimator.ofPropertyValuesHolder(congratulations, PropertyValuesHolder.ofFloat("scaleX", 0.95f), PropertyValuesHolder.ofFloat("scaleY", 0.95f), PropertyValuesHolder.ofFloat("rotation", 5));
            animatorCongratz.setDuration(300);
            animatorCongratz.setRepeatCount(ObjectAnimator.INFINITE);
            animatorCongratz.setRepeatMode(ObjectAnimator.REVERSE);
            animatorCongratz.start();
        } else {
            congratulations.setVisibility(TextView.GONE);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.LOGO_PRE_MENU), PropertyValuesHolder.ofFloat("scaleX", 0.95f), PropertyValuesHolder.ofFloat("scaleY", 0.95f), PropertyValuesHolder.ofFloat("rotation", -2));
            animator.setDuration(500);
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setRepeatMode(ObjectAnimator.REVERSE);
            animator.start();
        }
        Data.saveData(this);
    }

    @Override
    protected String getMusic() {
        return null;
    }

    private void startGame(View v){
        Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void back(View v){
        Intent intent = new Intent(GameOverActivity.this, MenuActivity.class);
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