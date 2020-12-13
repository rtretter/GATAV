package de.hskl.gatav.flappybender.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Entity {

    // Position
    protected double x, y;
    // Velocity
    protected double velX, velY;
    // Size
    protected int width, height;
    // Graphics
    protected Bitmap bitmap;

    public Entity(int x, int y, int width, int height, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
    }

    public void tick(Canvas canvas) {
        x += velX;
        y += velY;
    }

    public abstract void render(Canvas canvas);

}
