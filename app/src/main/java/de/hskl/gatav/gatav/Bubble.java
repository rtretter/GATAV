package de.hskl.gatav.gatav;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Bubble {

    private float x, y, speed;

    private static final Paint paint = new Paint();

    static {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        paint.setAlpha(150);
        paint.setAntiAlias(true);
    }

    public static final int RADIUS = 10;
    public static final int MAX_SPEED = 10;
    public static final int MIN_SPEED = 1;

    public Bubble(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = Math.max(speed, MIN_SPEED);
    }

    public void draw(Canvas c) {
        c.drawOval(new RectF(x-RADIUS, y-RADIUS, x + RADIUS, y + RADIUS), paint);
    }

    public void move() {
        y -= speed;
    }

    public boolean outOfRange() {
        return (y+RADIUS < 0);
    }
}
