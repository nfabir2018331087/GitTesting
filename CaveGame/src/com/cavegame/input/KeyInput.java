package com.cavegame.input;

import com.cavegame.Game;
import com.cavegame.Id;
import com.cavegame.entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0;i<Game.handler.entities.size();i++){
            Entity en = Game.handler.entities.get(i);
            if(en.getId() == Id.player){
            switch (key) {
                case KeyEvent.VK_W:
                    if (!en.jumping) {
                        en.jumping = true;
                        en.gravity = 8.0;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!en.jumping) {
                        en.jumping = true;
                        en.gravity = 10.0;
                    }
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(-5);
                    en.facing = 0;
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(5);
                    en.facing = 1;
                    break;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0;i<Game.handler.entities.size();i++){
            Entity en = Game.handler.entities.get(i);
            if(en.getId()==Id.player){
            switch (key) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_SPACE:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                    en.setVelX(0);
                    break;
                }
            }
        }
    }
}
