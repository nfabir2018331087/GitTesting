package com.cavegame;

import com.cavegame.entity.Entity;
import com.cavegame.gfx.Sprite;
import com.cavegame.gfx.SpriteSheet;
import com.cavegame.gfx.gui.Launcher;
import com.cavegame.gfx.gui.Launcher;
import com.cavegame.input.KeyInput;
import com.cavegame.input.MouseInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Game extends Canvas implements Runnable {
    public static final int width = 320;
    public static final int height = 180;
    public static final int scale = 4;
    public static final String title = "CaveGame";

    public static int mins = 0,secs = 0;
    public static int startingTime = 0;
    public static int coins = 0;
    public static int deathScreenTime = 0;
    public static boolean deathScreen = false;
    public static boolean playing = false;
    public static boolean winningScreen = false;
    public static int winningScreenTime = 0;

    private Thread thread;
    private boolean running = false;
    private BufferedImage image;

    public static Time time;
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Sprite mud;
    public static Sprite[] player;
    public static Sprite battery;
    public static Sprite bomb;
    public static Sprite coin;
    public static Sprite cup;

    public static Camera cam;
    public static Launcher launcher;
    public static MouseInput mouse;

    public Game() {
        Dimension size = new Dimension(width*scale,height*scale);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }

    public void tick(){

        if(playing) {
            handler.tick();
            startingTime++;
            mins = startingTime / 3600;
            secs = startingTime / 60 % 60;
        }
        for(int i = 0;i<handler.entities.size();i++){
            Entity en = handler.entities.get(i);
            if(en.getId()==Id.player) cam.tick(en);
        }
        if(winningScreen){
            handler.clearLevel();
            winningScreenTime++;
        }
        if(winningScreenTime>=300){
            playing = false;
            startingTime = 0;
            winningScreenTime = 0;
            winningScreen = false;
            handler.createLevel(image);
        }
        if(deathScreen) {
            handler.clearLevel();
            deathScreenTime++;
        }
        if(deathScreenTime>=180){
            playing = false;
            startingTime = 0;
            deathScreenTime = 0;
            deathScreen = false;
            handler.createLevel(image);
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,getWidth(),getHeight());
        if(winningScreen){
            String text = "You Won";
            g.setColor(Color.white);
            g.setFont(new Font("Broadway",Font.BOLD,100));

            FontMetrics fm = g.getFontMetrics();
            int x = (getFrameWidth() - fm.stringWidth(text)) / 2;
            int y = ((getFrameHeight() - fm.getHeight()) / 2) + fm.getAscent();

            g.drawString(text,x,y);
        }
        if(deathScreen){
            String text = "Game Over";
            g.setColor(Color.white);
            g.setFont(new Font("Broadway",Font.ITALIC,100));

            FontMetrics fm = g.getFontMetrics();
            int x = (getFrameWidth() - fm.stringWidth(text)) / 2;
            int y = ((getFrameHeight() - fm.getHeight()) / 2) + fm.getAscent();

            g.drawString(text,x,y);
            g.setFont(new Font("Broadway",Font.BOLD,80));
            g.drawString("You Lose!", x+80,y-200);
        }
        if(!winningScreen && !deathScreen) {
            g.drawImage(coin.getBufferedImage(), 0, 5, 75, 75, null);
            g.setColor(Color.white);
            g.setFont(new Font("Courier", Font.BOLD, 20));
            g.drawString("x " + coins, 75, 70);
            g.setFont(new Font("Courier", Font.BOLD, 50));
            g.drawString(mins + ":" + secs, getFrameWidth()-110,50);
        }
        if(!playing) launcher.render(g);
        else {
            g.translate(cam.getX(),cam.getY());
            handler.render(g);
        }
        g.dispose();
        bs.show();
    }

    private void init(){
        handler = new Handler();
        sheet = new SpriteSheet("/sprite.png");
        cam = new Camera();
        launcher = new Launcher();
        mouse = new MouseInput();

        addKeyListener(new KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        mud = new Sprite(sheet,5,1);
        player = new Sprite[4];
        battery = new Sprite(sheet, 6, 1);
        bomb = new Sprite(sheet, 7, 1);
        coin = new Sprite(sheet, 8, 1);
        cup = new Sprite(sheet, 1,2);

        for (int i=0;i<player.length;i++){
            player[i] = new Sprite(sheet,i+1,1);
        }

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/level.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.createLevel(image);
    }

    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int ticks=0;
        int frames=0;

        while (running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while (delta>=1){
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                System.out.println(frames + " fps, " + ticks + " tps");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this,"Thread");
        thread.start();
    }

    private synchronized void stop(){
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getFrameWidth(){
        return width*scale;
    }

    public static int getFrameHeight(){
        return height*scale;
    }

    public static Rectangle getVisibleArea(){
        for(int i = 0;i<handler.entities.size();i++){
            Entity en = handler.entities.get(i);
            if(en.getId()==Id.player)
                return new Rectangle(-cam.getX(),-cam.getY(),getFrameWidth(),getFrameHeight());
        }
        return null;
    }
}
