package de.hskl.gatav.gatav;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;

    private GameLoop gameLoop;

    private List<Bubble> bubbles = new ArrayList<>();
    private float BUBBLE_FREQUENCY = 0.3f;

    private Paint backgroundPaint = new Paint();
    private static final int RADIUS = 10;
    private static final float DEG_TO_RAD = (float) (2*3.141529/360.0);

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback((SurfaceHolder.Callback) this);

        backgroundPaint.setColor(Color.BLUE);
    }

    private void drawScreen(Canvas c) {
        c.drawRect(0, 0, c.getWidth(), c.getHeight(), backgroundPaint);

        for(Bubble b : bubbles) {
            b.draw(c);
        }
    }

    private void calculateDisplay(Canvas canvas) {

        randomlyAddBubbles(canvas.getWidth(), canvas.getHeight());

        List<Bubble> bubblesToRemove = new ArrayList<>();

        for(Bubble b: bubbles) {
            b.move();
            if(b.outOfRange()) {
                bubblesToRemove.add(b);
            }
        }

        for(Bubble b: bubblesToRemove) {
            bubbles.remove(b);
        }
    }

    public void randomlyAddBubbles(int screenWidth, int screenHeight) {
        if(Math.random() > BUBBLE_FREQUENCY) {
            return;
        }
        bubbles.add(new Bubble((int) (screenWidth * Math.random()), screenHeight + Bubble.RADIUS, (int)((Bubble.MAX_SPEED - 0.1) * Math.random() + 0.1)));
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        synchronized (this) {
            if(gameLoop == null) {
                gameLoop = new GameLoop();
                gameLoop.start();
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        synchronized (this) {
            boolean retry = true;

            if(gameLoop != null) {
                gameLoop.running = false;

                while(retry) {
                    try {
                        gameLoop.join();
                        retry = false;
                    } catch(InterruptedException e) {

                    }
                }
            }
            gameLoop = null;
        }
    }

    private class GameLoop extends Thread {

        private long msPerFrame = 1000 / 30;
        private boolean running = true;
        private long frameTime = 0;

        public void run() {
            Canvas canvas = null;
            final SurfaceHolder surfaceHolder = GameView.this.surfaceHolder;

            frameTime = System.currentTimeMillis();

            while(running) {
                try {
                    canvas = surfaceHolder.lockCanvas();

                    synchronized (surfaceHolder) {
                        calculateDisplay(canvas);
                        drawScreen(canvas);
                    }
                } finally {
                    if(canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                waitTillNextFrame();
            }
        }

        private void waitTillNextFrame() {
            long nextSleep = 0;
            frameTime += msPerFrame;
            nextSleep = frameTime - System.currentTimeMillis();

            if(nextSleep > 0) {
                try {
                    sleep(nextSleep);
                } catch(InterruptedException e) {

                }
            }
        }
    }
}
