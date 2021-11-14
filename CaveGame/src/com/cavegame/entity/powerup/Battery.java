package com.cavegame.entity.powerup;

import com.cavegame.Game;
import com.cavegame.Handler;
import com.cavegame.Id;
import com.cavegame.entity.Entity;
import com.cavegame.tile.Tile;

import java.awt.*;
import java.util.Random;

public class Battery extends Entity {

    private Random random = new Random();

    public Battery(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);

        int dir = random.nextInt(2);
        switch (dir){
            case 0:
                setVelX(-5);
                break;
            case 1:
                setVelX(5);
                break;
        }

    }

    public void tick() {
        x+=velX;
        y+=velY;

        for(int i = 0;i<handler.tiles.size();i++){
            Tile ti = handler.tiles.get(i);
            if(!ti.solid) break;
            if(ti.getId()==Id.wall){
                if(getBoundsBottom().intersects(ti.getBounds())){
                    setVelY(0);
                    if(falling) falling=false;
                }
                if(getBoundsLeft().intersects(ti.getBounds())){
                    setVelX(2);
                }
                if(getBoundsRight().intersects(ti.getBounds())){
                    setVelX(-2);
                }
                else if(!falling){
                    gravity =0.7;
                    falling = true;
                }
            }
        }
        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
        }
    }

    public void render(Graphics g) {
        g.drawImage(Game.battery.getBufferedImage(),x,y,width,height,null);
    }
}
