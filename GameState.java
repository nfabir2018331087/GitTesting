import java.awt.*;

public class GameState extends State{
    private Player player;
    private World world;
    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"Resource/Worlds/world1.txt");
        handler.setWorld(world);
        player= new Player(handler,0,0,Creatures.DEFAULT_CWIDTH,Creatures.DEFAULT_CHEIGHT);

    }
    public void tick() {
        world.tick();
        player.tick();
    }
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
