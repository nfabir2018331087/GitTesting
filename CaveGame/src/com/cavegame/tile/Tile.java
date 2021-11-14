package com.cavegame.tile;

import com.cavegame.Handler;
import com.cavegame.Id;

import java.awt.*;

public abstract class Tile {

    public int x,y,width,height,velX,velY;
    public boolean solid;
    public Id id;
    public Handler handler;

    public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void die(){
        handler.removeTile(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public boolean isSolid() {
        return solid;
    }

    public Id getId(){
        return id;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}

