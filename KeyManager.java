import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean[] keys;
    public boolean up,down,left,right;

    public KeyManager(){
        keys = new boolean[256];
    }
    public void tick(){
        up=keys[KeyEvent.VK_UP];
        down=keys[KeyEvent.VK_DOWN];
        left=keys[KeyEvent.VK_LEFT];
        right=keys[KeyEvent.VK_RIGHT];
    }
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()]=true;
        System.out.println("Pressed");
    }

    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()]=false;
    }
}
