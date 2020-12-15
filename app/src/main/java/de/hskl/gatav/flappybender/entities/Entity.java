package de.hskl.gatav.flappybender.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import androidx.constraintlayout.solver.widgets.Rectangle;

import de.hskl.gatav.flappybender.graphics.Asset;

public abstract class Entity {

    // Position
    protected double x, y;
    // Velocity
    protected double velX, velY;
    // Size
    protected int width, height;
    // Graphics
    protected Asset asset;
    // zDepth of Entity
    protected int zPos;


    public Entity(int x, int y, int width, int height, Asset asset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.asset = asset;
        this.zPos = 0;
        this.setSize(width, height);
    }

    public void tick(Canvas canvas) {
        x += velX;
        y += velY;
    }

    public void render(Canvas canvas) {
        asset.render(canvas, x, y);
    }

    public Rect getBounds() {
        return new Rect((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.asset = this.asset.getResized(width, height);
    }

}
