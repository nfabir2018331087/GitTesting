public abstract class Creatures extends Entity {
    public static final int DEFAULT_HEALTH=10,DEFAULT_CWIDTH=64,DEFAULT_CHEIGHT=64;
    public static final float DEFAULT_SPEED= 2.0f;
    protected int health;
    protected float speed;
    protected float xMove,yMove;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public Creatures(Handler handler,float x, float y, int width, int height){
        super(handler,x,y,width,height);
        health=DEFAULT_HEALTH;
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }
    public void move(){
       xMove();
       yMove();
    }
    public void xMove(){
        if(xMove>0){
            int tx = (int) (x+xMove+bounds.x+bounds.width)/Tile.TILEWIDTH;
            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEHEIGHT)&&
                    !collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT))
                x+=xMove;
            else x = tx*Tile.TILEWIDTH-bounds.x-bounds.width-1;
        }
        else if(xMove<0){
            int tx = (int) (x+xMove+bounds.x)/Tile.TILEWIDTH;
            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEHEIGHT)&&
                    !collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT))
                x+=xMove;
            else x = tx* Tile.TILEWIDTH+Tile.TILEWIDTH-bounds.x;
        }

    }
    public void yMove(){
        if(yMove<0){
            int ty = (int) (y+yMove+bounds.y)/Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEWIDTH,ty)&&
                    !collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))
                y+=yMove;
            else y = ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.y;
        }
        else if(yMove>0){
            int ty = (int) (y+yMove+bounds.y+bounds.height)/Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEWIDTH,ty)&&
                    !collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))
                y+=yMove;
            else y = ty*Tile.TILEHEIGHT-bounds.y-bounds.height-1;
        }

    }
    protected boolean collisionWithTile(int x,int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }

}
