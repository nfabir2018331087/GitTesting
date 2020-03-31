import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class MainGame implements Runnable{
    private Display display;
    private String title;



    private int width,height;
    public boolean running;

    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage image;
    private SpriteSheet sheet;

    private State gameState;
    private State menuState;

    private KeyManager keyManager;

    private Camera camera;

    private Handler handler;

    public MainGame(String title, int width,int height){
        this.title=title;
        this.width=width;
        this.height=height;
        keyManager= new KeyManager();
    }
    private void init(){
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        camera = new Camera(handler,0,0);

        gameState=new GameState(handler);
        menuState=new MenuState(handler);
        State.setState(gameState);
    }
    private void tick(){
        keyManager.tick();
        if(State.getState()!=null) State.getState().tick();
    }
    private void render(){
        bs=display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g=bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        if(State.getState()!=null) State.getState().render(g);
        bs.show();
        g.dispose();
    }
    public void run(){
        init();
        int fps=60;
        double timePerTick=1000000000/fps;
        double delta=0;
        long now;
        long lastTime=System.nanoTime();
        long timer=0;
        int ticks=0;
        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            timer+=now-lastTime;
            lastTime=now;

            if(delta>=1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>=1000000000){
                System.out.println("Frames per Second: "+ ticks);
                ticks=0;
                timer=0;
            }
        }
        stop();
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    public Camera getCamera(){
        return camera;
    }
    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public synchronized void start(){
        if(running) return;
        running=true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running) return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
