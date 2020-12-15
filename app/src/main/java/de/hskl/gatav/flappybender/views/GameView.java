package de.hskl.gatav.flappybender.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import de.hskl.gatav.flappybender.entities.Entity;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Obstacle;
import de.hskl.gatav.flappybender.entities.Player;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.logic.LevelGenerator;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "GAME";

    // Game Loop Ticks und Frames pro Sekunde
    private static final double TPS = 60.0;

    private final Paint backgroundPaint;

    private boolean isRunning;
    private Thread loopThread;

    private SurfaceHolder surfaceHolder = null;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);

        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (isRunning) {
            throw new RuntimeException("Game already running?!?");
        }
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
        loopThread = new Thread(this::gameLoop);
        loopThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (!isRunning) {
            throw new RuntimeException("Game stopped but isn't running?!?");
        }
        isRunning = false;
        try {
            loopThread.join();
            loopThread = null;
        } catch (InterruptedException e) {
            Log.e(TAG, "Error Destroying surface: " + e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gameLoop() {
        long time1 = System.nanoTime();
        double nsPerTick = 1000000000 / TPS;
        double delta = 0.0;

        while (isRunning) {
            long time2 = System.nanoTime();
            delta += (time2 - time1) / nsPerTick;
            time1 = time2;

            if (delta >= 1) {
                delta--;
                Canvas canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    tick(canvas);
                    render(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            sleep(1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void tick(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        Game.getInstance().tick(canvas);
    }

    private void render(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        Game.getInstance().render(canvas);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Log.e(TAG, "Error Sleeping: " + e.getMessage());
        }
    }


}
