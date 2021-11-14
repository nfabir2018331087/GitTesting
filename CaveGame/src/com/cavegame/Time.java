package com.cavegame;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Time {

   /* public void render(Graphics g){
        g.drawImage(Game.coin.getBufferedImage(), 0, 5, 75, 75, null);
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("x " + Game.coins, 75, 70);
        g.setFont(new Font("Courier", Font.BOLD, 50));
        g.drawString(Game.mins + ":" + Game.secs, Game.getFrameWidth()-110,50);
    }*/
   /* public static String getFormattedTime(){
        StringBuilder stringBuilder = new StringBuilder();
        int mins = updateSinceStart / Game.ticks / 60;
        int secs = updateSinceStart / Game.ticks % 60;

        if(mins<10) stringBuilder.append(0);
        stringBuilder.append(mins);
        stringBuilder.append(":");
        if(secs<10) stringBuilder.append(0);
        stringBuilder.append(secs);

        return stringBuilder.toString();
    }*/
}
