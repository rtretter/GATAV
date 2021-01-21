package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import de.hskl.gatav.flappybender.graphics.Asset;

public class Particle extends Entity {

    private Paint paint;

    public Particle(int x, int y, int width, int height, double velX, double velY, int color) {
        super(x, y, width, height, null);
        this.velX = velX;
        this.velY = velY;
        this.paint = new Paint();
        paint.setColor(color);
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawCircle((int) x, (int) y, width, paint);
    }
}
