package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;

public abstract class Entity {

    // Position
    protected double x, y;
    // Velocity
    protected double velX, velY;
    // Size
    protected int width, height;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick(Canvas canvas) {
        x += velX;
        y += velY;
    }

    public abstract void render(Canvas canvas);

}
