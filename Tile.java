import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    protected BufferedImage image;
    protected int id;
    public static final int TILEWIDTH=64,TILEHEIGHT=64;

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile stoneTile = new StoneTile(1);
    public static Tile mudTile = new MudTile(2);

    public Tile(BufferedImage image,int id){
        this.image=image;
        this.id=id;

        tiles[id]=this;
    }
    public void tick(){

    }
    public void render(Graphics g,int x,int y){
        g.drawImage(image,x,y,TILEWIDTH,TILEHEIGHT,null);
    }
    public boolean isSolid(){
        return false;
    }
    public int getId(){
        return id;
    }
}