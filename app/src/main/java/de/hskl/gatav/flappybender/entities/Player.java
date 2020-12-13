package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player extends Entity {

    private static final Paint paint = new Paint();

    static {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        paint.setAlpha(150);
        paint.setAntiAlias(true);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        velX = 1;
        velY = 0.5;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect((float) x, (float) y, (float) (x + width), (float) (y + height), paint);
    }
}
