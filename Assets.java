import java.awt.image.BufferedImage;

public class Assets {
    private static int width=64,height=64;
    public static BufferedImage player,grass,mud,stone,tree;

    public static void init(){
        SpriteSheet sheet=new SpriteSheet(ImageLoder.LoadImage("/Images/img.png"));
        grass=sheet.crop(0,0,width,height);
        stone=sheet.crop(width,0,width,height);
        mud=sheet.crop(width*2,0,width,height);
        tree=sheet.crop(width*3,0,width,height);
        player=ImageLoder.LoadImage("/Images/tr.png");
    }
}
