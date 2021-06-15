package com.cavegame.gfx.gui;

import com.cavegame.Game;

import java.awt.*;

public class Launcher {

    public Button[] buttons;
    public Launcher(){
        buttons = new Button[2];
        buttons[0] = new Button(Game.getFrameWidth()/2-110,Game.getFrameHeight()/2-100,170,50,"Start Game");
        buttons[1] = new Button(Game.getFrameWidth()/2-103,Game.getFrameHeight()/2+100,160,50,"Exit Game");
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0, Game.getFrameWidth(),Game.getFrameHeight());

        for(int i = 0; i<buttons.length; i++){
            buttons[i].render(g);
        }
    }
}
