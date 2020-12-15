package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Rect;

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


    public Entity(int x, int y, int width, int height, Asset asset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.asset = asset;
    }

    public Entity(int x, int y, int width, Asset asset) {
        this(x, y, width, (int)((float) width / asset.getWidth() * asset.getHeight()), asset);
    }

    public void tick(Canvas canvas) {
        x += velX;
        y += velY;
    }

    public void render(Canvas canvas) {
        if(height == 0) {
            asset.render(canvas, x, y, width);
        } else {
            asset.render(canvas, x, y, width, height);
        }
    }

    public Rect getBounds(){
        return new Rect((int)x,(int)y,(int)x+width,(int)y+height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
