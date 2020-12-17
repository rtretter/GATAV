package de.hskl.gatav.flappybender.logic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.logging.Level;

import de.hskl.gatav.flappybender.entities.Background;
import de.hskl.gatav.flappybender.entities.Entity;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.LevelBackground;
import de.hskl.gatav.flappybender.entities.Player;
import de.hskl.gatav.flappybender.entities.SkyBackground;
import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.views.GameOverActivity;
import de.hskl.gatav.flappybender.views.MainActivity;

public class Game {

    private static final int FONT_SIZE = 128;
    private static Game INSTANCE;

    private Context context;
    private int highscore;
    private int score;
    private int lastScore;

    private boolean wasNewHighscore;

    private Asset playerSkin;

    private Paint scorePaint;
    private boolean shouldRestart;

    private Game() {
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(FONT_SIZE);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        scorePaint.setAntiAlias(true);
        playerSkin = AssetHandler.getAsset(Asset.ASSET_BENDER_STANDARD);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickAction() {
        LevelGenerator.getInstance().startSpawning();
        Player p = EntityHandler.getInstance().getPlayer();
        if(p == null) {
            return;
        }
        p.jump();
    }

    public void incrementScore() {
        score++;
    }

    public void restart() {
        shouldRestart = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void restartAction(Canvas canvas) {
        LevelGenerator.getInstance().restart();
        EntityHandler.getInstance().clear();
        EntityHandler.getInstance().addEntity(new SkyBackground(0, canvas.getHeight()));
        EntityHandler.getInstance().addEntity(new LevelBackground(0, canvas.getHeight()));
        EntityHandler.getInstance().addEntity(new Player(100, canvas.getHeight() / 2 - Player.PLAYER_HEIGHT / 2));
    }

    public void gameOver() {
        restart();
        wasNewHighscore = score > highscore;
        lastScore = score;
        highscore = Math.max(score, highscore);
        score = 0;
        Intent intent = new Intent(context, GameOverActivity.class);
        context.startActivity(intent);
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }

    public boolean wasNewHighscore() {
        return wasNewHighscore;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPlayerSkin(Asset skin) {
        this.playerSkin = skin;
    }

    public Asset getPlayerSkin() {
        return playerSkin;
    }

    public static Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

}
