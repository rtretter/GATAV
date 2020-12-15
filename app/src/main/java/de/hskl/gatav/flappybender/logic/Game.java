package de.hskl.gatav.flappybender.logic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import de.hskl.gatav.flappybender.entities.Background;
import de.hskl.gatav.flappybender.entities.Entity;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Player;

public class Game {

    private static final int FONT_SIZE = 128;
    private static Game INSTANCE;
    private int score;

    private Paint scorePaint;
    private boolean shouldRestart;

    private Game() {
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(FONT_SIZE);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        scorePaint.setAntiAlias(true);
        restart();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void tick(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        if(shouldRestart) {
            shouldRestart = false;
            restartAction(canvas);
        }
        EntityHandler.getInstance().tick(canvas);
        LevelGenerator.getInstance().tick(canvas);
    }

    public void render(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        EntityHandler.getInstance().render(canvas);
        canvas.drawText(score + "", canvas.getWidth() / 2, (int) (FONT_SIZE * 1.25), scorePaint);
    }

    public void incrementScore() {
        score++;
    }

    public void restart() {
        shouldRestart = true;
    }

    private void restartAction(Canvas canvas) {
        LevelGenerator.getInstance().restart();
        EntityHandler.getInstance().clear();
        EntityHandler.getInstance().addEntity(new Background(0, canvas.getHeight()));
        EntityHandler.getInstance().addEntity(new Player(100, 100, 175));
        score = 0;
    }

    public static Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

}
