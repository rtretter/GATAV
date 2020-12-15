package de.hskl.gatav.flappybender.entities;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.List;

import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;

public class Player extends Entity {

    public Player(int x, int y, int width) {
        super(x, y, width, AssetHandler.getAsset(Asset.ASSET_BENDER_PROF));
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, AssetHandler.getAsset(Asset.ASSET_BENDER_PROF));
    }

    public void jump(){
        velY=Math.max(Math.min(velY-20,-20),-50);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void tick(Canvas canvas) {
        super.tick(canvas);
        velY+=2;

        if(y<0 || y>canvas.getHeight()-height){
            y=canvas.getHeight()/2;
            velY=0;
        }

        List<Entity> obstacles = EntityHandler.getInstance().getObstacles();
        Rect playerRect=getBounds();
        for(int i=0;i<obstacles.size();i++){
                if (playerRect.intersect(obstacles.get(i).getBounds())){
                    EntityHandler.getInstance().clear();
                    EntityHandler.getInstance().addEntity(new Player(100,canvas.getHeight()/2,200));

                }
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect((int)x+10,(int)y+10,(int)x+width-20,(int)y+height-20);
    }
}
