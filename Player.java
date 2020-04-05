import java.awt.*;

public class Player extends Creatures {
    public Player(Handler handler,float x,float y,int width,int height){
        super(handler,x,y,width,height);

        bounds.x=24;
        bounds.y=40;
        bounds.width=12;
        bounds.height=16;
    }
    public void tick() {
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
    }
    private void getInput(){
        xMove=0;
        yMove=0;
        if(handler.getKeyManager().up) yMove= -speed;
        if(handler.getKeyManager().down) yMove= speed;
        if(handler.getKeyManager().left) xMove= -speed;
        if(handler.getKeyManager().right) xMove= speed;
    }
    public void render(Graphics g) {
        g.drawImage(Assets.player,(int)(x-handler.getCamera().getxOffset()),(int)(y-handler.getCamera().getyOffset()),
                width,height,null);
    }
}
