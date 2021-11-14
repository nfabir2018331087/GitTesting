package com.cavegame.entity.mob;

import com.cavegame.Game;
import com.cavegame.Handler;
import com.cavegame.Id;
import com.cavegame.entity.Entity;
import com.cavegame.states.PlayerState;
import com.cavegame.tile.Tile;

import java.awt.*;

public class Player extends Entity {
    private PlayerState state;
    private int frame = 0;
    private int frameDelay = 0;

    public Player(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
        state = PlayerState.Small;
    }

    public void tick() {
        x+=velX;
        y+=velY;
        if(y>handler.wallY*64+128) die();
        for(int i = 0;i<handler.tiles.size();i++){
            Tile ti = handler.tiles.get(i);
            if(!ti.solid) break;
            if(ti.getId()==Id.wall){
                if(getBoundsTop().intersects(ti.getBounds())&&(ti.getId()!=Id.coin)){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        gravity = 0.7;
                        falling = true;
                    }
                }
                else if(!jumping && !falling){
                    gravity =0.7;
                    falling = true;
                }
                if(getBoundsBottom().intersects(ti.getBounds())&&(ti.getId()!=Id.coin)){
                    setVelY(0);
                    if(falling) falling=false;
                }
                if(getBoundsLeft().intersects(ti.getBounds())&&(ti.getId()!=Id.coin)){
                    setVelX(0);
                    x = ti.getX()+ti.width;
                }
                if(getBoundsRight().intersects(ti.getBounds())&&(ti.getId()!=Id.coin)){
                    setVelX(0);
                    x = ti.getX()-width;
                }
            }
            if(getBounds().intersects(ti.getBounds())&&(ti.getId()==Id.coin)){
                Game.coins++;
                ti.die();
            }
            if(getBounds().intersects(ti.getBounds())&&ti.getId()==Id.cup){
                Game.winningScreen = true;
            }
        }
        for(int i = 0; i<handler.entities.size();i++){
            Entity en = handler.entities.get(i);
            if(en.getId() == Id.battery){
                if(getBounds().intersects(en.getBounds())){
                    int tpX=getX(),tpY=getY();
                    width+=16;height+=16;
                    setX(tpX-width);
                    setY(tpY-height);
                    if(state == PlayerState.Small) state = PlayerState.Big;
                    en.die();
                }
            }
            else if(en.getId() == Id.bomb){
                if(getBounds().intersects(en.getBounds())){
                    if(state == PlayerState.Big){
                        en.die();
                        Game.coins += 10;
                        width-=16;height-=16;
                        state = PlayerState.Small;
                    }
                    else {
                        die();
                        en.die();
                    }
                }
            }
        }
        if(jumping){
            gravity-=0.15;
            setVelY((int)-gravity);
            if(gravity<=0.0){
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            gravity+=0.15;
            setVelY((int)gravity);
        }
        if(velX!=0) {
            frameDelay++;
            if (frameDelay >= 10) {
                frame++;
                if (frame >= 2) {
                    frame = 0;
                }
                frameDelay = 0;
            }
        }
    }

    public void render(Graphics g) {
        if (facing == 0) g.drawImage(Game.player[frame + 2].getBufferedImage(), x, y, width, height, null);
        else if (facing == 1) g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
    }
}
