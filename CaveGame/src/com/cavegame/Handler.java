package com.cavegame;

import com.cavegame.entity.Entity;
import com.cavegame.entity.mob.Bomb;
import com.cavegame.entity.mob.Player;
import com.cavegame.entity.powerup.Battery;
import com.cavegame.tile.Coin;
import com.cavegame.tile.Cup;
import com.cavegame.tile.Tile;
import com.cavegame.tile.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {

    public int x,y,wallX,wallY;

    public LinkedList<Entity> entities = new LinkedList<Entity>();
    public LinkedList<Tile> tiles = new LinkedList<Tile>();

    public void tick(){
        for (int i = 0;i<entities.size();i++) {
            Entity en = entities.get(i);
            en.tick();
        }
        for (int i = 0;i<tiles.size();i++) {
            Tile ti = tiles.get(i);
            if(Game.getVisibleArea()!=null&&ti.getBounds().intersects(Game.getVisibleArea())) ti.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0;i<entities.size();i++) {
            Entity en = entities.get(i);
            if(Game.getVisibleArea()!=null&&en.getBounds().intersects(Game.getVisibleArea())) en.render(g);
        }
        for(int i = 0;i<tiles.size();i++) {
            Tile ti = tiles.get(i);
            if(Game.getVisibleArea()!=null&&ti.getBounds().intersects(Game.getVisibleArea()))ti.render(g);
        }
    }

    public void addEntity(Entity en){
        entities.add(en);
    }

    public void removeEntity(Entity en){
        entities.remove(en);
    }

    public void addTile(Tile ti){
        tiles.add(ti);
    }

    public void removeTile(Tile ti){
        tiles.remove(ti);
    }

    public void createLevel(BufferedImage level){
        int width = level.getWidth();
        int height = level.getHeight();

        for (y = 0; y < height; y++){
            for (x = 0; x < width; x++){
                int pixel = level.getRGB(x,y);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 0 && green == 0 && blue == 0) {
                    addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.wall, this));
                    wallX = x;
                    wallY = y;
                }
                if(red == 0 && green == 0 && blue == 255)
                    addEntity(new Player(x*64,y*64,32,32,Id.player,this));
                if(red == 255 && green == 0 && blue == 0)
                    addEntity(new Battery(x*64,y*64,64,64,Id.battery,this));
                if(red == 0 && green == 255 && blue == 255)
                    addEntity(new Bomb(x*64,y*64,64,64,Id.bomb,this));
                if(red == 255 && green == 255 && blue == 0)
                    addTile(new Coin(x*64,y*64,48,48,true,Id.coin,this));
                if(red == 255 && green == 0 && blue == 255)
                    addTile(new Cup(x*64,y*64,64,64,true,Id.cup,this));
            }
        }
    }
    public void clearLevel(){
        entities.clear();
        tiles.clear();
    }
}
