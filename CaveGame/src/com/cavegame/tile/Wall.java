package com.cavegame.tile;

import com.cavegame.Game;
import com.cavegame.Handler;
import com.cavegame.Id;

import java.awt.*;

public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Game.mud.getBufferedImage(),x,y,width,height,null );

    }
}
