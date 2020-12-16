package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.List;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.sound.Discman;

public class Player extends Entity {

    private static final double MAX_JUMP_SPEED = -20;
    private static final double JUMP_SPEED = -15;
    private static final double DEFAULT_FORCE = 8;
    private static final double DEFAULT_MASS = 1;

    public static final int PLAYER_HEIGHT = 275;

    private boolean shouldDrop;

    private double t = 0.0;
    private double dt = 0.1f;

    private double force = DEFAULT_FORCE;
    private double mass = DEFAULT_MASS;

    public Player(int x, int y) {
        this(x, y, (int) ((float) PLAYER_HEIGHT / Game.getInstance().getPlayerSkin().getHeight() * Game.getInstance().getPlayerSkin().getWidth()), PLAYER_HEIGHT);
    }

    private Player(int x, int y, int width, int height) {
        super(x, y, width, height, Game.getInstance().getPlayerSkin());
    }

    public void jump() {
        shouldDrop = true;
        velY += JUMP_SPEED;
        if (velY > JUMP_SPEED) {
            velY = JUMP_SPEED;
        }
        if (velY < MAX_JUMP_SPEED) {
            velY = MAX_JUMP_SPEED;
        }
        t = 0;
    }

    private void move() {
        force = Math.max(-velY * 0.25, DEFAULT_FORCE);
        if (t <= 10.0) {
            y = y + velY * dt;
            velY = velY + (force / mass) * dt;
            t += dt;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void tick(Canvas canvas) {
        super.tick(canvas);
        if(!shouldDrop) {
            return;
        }
        move();

        if(y < 0) {
            y = 0;
            velY = 0;
        }

        if (y > canvas.getHeight() - height) {
            Game.getInstance().gameOver();
        }

        List<Entity> obstacles = EntityHandler.getInstance().getObstacles();
        Rect playerRect = getBounds();
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle tmpObstacle = (Obstacle) obstacles.get(i);
            if(x > tmpObstacle.x + tmpObstacle.width) {
                if(tmpObstacle.playedPassed()) {
                    Game.getInstance().incrementScore();
                }
            }
            if (playerRect.intersect(obstacles.get(i).getBounds())) {
                Game.getInstance().gameOver();
            }
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect((int) x + 10, (int) y + 10, (int) x + width - 20, (int) y + height - 20);
    }
}
