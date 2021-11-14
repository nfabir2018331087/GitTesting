package com.cavegame.input;

import com.cavegame.Game;
import com.cavegame.gfx.gui.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    public int x,y;

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        for(int i = 0;i< Game.launcher.buttons.length;i++){
            Button b = Game.launcher.buttons[i];
            if(x>=b.getX()&&y>=b.getY()&&x<=b.getX()+b.getWidth()&&y<=b.getY()+b.getHeight()) b.triggerEvent();
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

    }
}
