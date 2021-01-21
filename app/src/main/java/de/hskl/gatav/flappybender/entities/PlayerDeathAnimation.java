package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeathAnimation extends Entity {

    private static final int[] explosionColors = {Color.rgb(200, 100, 33)
            , Color.rgb(200, 130, 33)
            , Color.rgb(200, 33, 33)
            , Color.rgb(255, 0, 0)
            , Color.rgb(200, 0, 255)
            , Color.rgb(33, 255, 0)
            , Color.rgb(33, 180, 180)};
    private static final int particleAmount = 25;
    private static final int particleTickAmount = 10;
    private static final double particleSpeedFactor = 15;

    private int ticks;
    private Runnable actionAfter;
    private List<Particle> particles;

    public PlayerDeathAnimation(int x, int y, int w, int h, Runnable actionAfter) {
        super(x, y, w, h, null);
        this.actionAfter = actionAfter;
        this.particles = new ArrayList<>();
        this.ticks = 0;

        int centerX = x + w / 2;
        int centerY = y + h / 2;

        for(int i = 0 ; i < particleAmount; i++) {
            double particleVelX = (Math.random() * 2 - 1) * particleSpeedFactor;
            double particleVelY = (Math.random() * 2 - 1) * particleSpeedFactor;
            Particle particle = new Particle(centerX, centerY, w / 10, h / 10, particleVelX, particleVelY, explosionColors[(int) (Math.random() * explosionColors.length)]);
            particles.add(particle);
        }

    }

    @Override
    public void tick(Canvas canvas) {
        if(ticks >= particleTickAmount) {
            particles.clear();
            actionAfter.run();
            return;
        }
        ticks++;
        for(int i = 0; i < particles.size(); i++) {
            particles.get(i).tick(canvas);
        }
    }

    @Override
    public void render(Canvas canvas) {
        for(int i = 0; i < particles.size(); i++) {
            particles.get(i).render(canvas);
        }
    }
}
