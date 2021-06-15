package com.cavegame;

import com.cavegame.entity.Entity;
import com.cavegame.entity.mob.Player;

public class Camera {
    public int x,y;

    public void tick(Entity player){
        setX(-player.getX()+Game.getFrameWidth()/2-player.width/2);
        setY(-player.getY()+Game.getFrameHeight()/2-player.height/2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        if (x>0) this.x = 0;
        else if(x<-(Game.handler.wallX*64)+Game.getFrameWidth()-64)
            this.x = -(Game.handler.wallX*64)+Game.getFrameWidth()-64;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        if (y>0) this.y = 0;
        else if(y<-(Game.handler.wallY*64)+Game.getFrameHeight()-64)
            this.y = -(Game.handler.wallY*64)+Game.getFrameHeight()-64;
    }
}
