package de.hskl.gatav.flappybender.logic;

import android.graphics.Canvas;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.logging.Level;

import de.hskl.gatav.flappybender.entities.Entity;
import de.hskl.gatav.flappybender.entities.EntityHandler;
import de.hskl.gatav.flappybender.entities.Obstacle;

public class LevelGenerator {

    private static LevelGenerator INSTANCE;
    private static final int TICK_GENERATE = 100;
    private static final int GAP = 500;
    private static final int MINIMUM_HEIGHT = 100;
    private int tickCounter = 150;


    private LevelGenerator(){}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void tick(Canvas canvas){
        tickCounter++;

        if(tickCounter>=TICK_GENERATE){
            int height = MINIMUM_HEIGHT+(int)(Math.random()*(canvas.getHeight()-GAP-MINIMUM_HEIGHT*2));
            EntityHandler.getInstance().addEntity(new Obstacle(canvas.getWidth(),0,height,Obstacle.OBSTACLE_TOP));
            EntityHandler.getInstance().addEntity(new Obstacle(canvas.getWidth(),height+GAP,canvas.getHeight()-(height+GAP),Obstacle.OBSTACLE_BOTTOM));
            tickCounter=0;
        }

        List<Entity> obstacles = EntityHandler.getInstance().getObstacles();

        for(int i=0;i<obstacles.size();i++){
            if(obstacles.get(i).getX()<-obstacles.get(i).getWidth()){
                EntityHandler.getInstance().removeEntity(obstacles.get(i));
            }
        }
    }

    public static LevelGenerator getInstance(){
        if (INSTANCE==null){
            INSTANCE=new LevelGenerator();
        }
        return INSTANCE;
    }
}
