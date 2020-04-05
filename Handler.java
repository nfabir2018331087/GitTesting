public class Handler {
    private MainGame game;
    private World world;

    public Handler(MainGame game){
        this.game = game;
    }

    public MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    public Camera getCamera(){
        return game.getCamera();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }


}
