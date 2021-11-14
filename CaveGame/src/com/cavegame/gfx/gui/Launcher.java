package com.cavegame.gfx.gui;

import com.cavegame.Game;

import java.awt.*;

public class Launcher {

    public Button[] buttons;
    public Launcher(){
        buttons = new Button[2];
        buttons[0] = new Button(Game.getFrameWidth()/2-110,Game.getFrameHeight()/2,170,50,"Start Game");
        buttons[1] = new Button(Game.getFrameWidth()/2-103,Game.getFrameHeight()/2+100,160,50,"Exit Game");
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0, Game.getFrameWidth(),Game.getFrameHeight());


        String text = "Cave Game";
        g.setColor(Color.white);
        g.setFont(new Font("Broadway",Font.BOLD,100));

        FontMetrics fm = g.getFontMetrics();
        int x = (Game.getFrameWidth() - fm.stringWidth(text)) / 2;
        int y = ((Game.getFrameHeight() - fm.getHeight()) / 2) + fm.getAscent();

        g.drawString(text,x,y-200);

        for(int i = 0; i<buttons.length; i++){
            buttons[i].render(g);
        }
    }
}
