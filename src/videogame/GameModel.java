package videogame;

import videogame.states.State;
import videogame.states.GameState;
import videogame.windows.MainWindow;
import videogame.graphics.SpriteSheet;
import videogame.graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Spring;
import videogame.input.KeyManager;
import videogame.input.MouseManager;

/**
 *
 * @author Emanuele Feola
 * @version 1.1, 2017-05-04
 */
public class GameModel implements Runnable{
    private static boolean booldebug = false;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private static int WIDTH;
    private static int HEIGHT;
    private MainWindow mainWindow;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bufferStrategy;
    private Graphics g;
    
    private BufferedImage testImage;
    private SpriteSheet sheet;
    
    private GameState gameState;
    
    public GameModel(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        keyManager =  new KeyManager();
        mouseManager = new MouseManager();
        start();
    }
    
    /**
     * method thats initialize the window, it is called once
     */
    public void init(){
        mainWindow = new MainWindow(WIDTH, HEIGHT);
        mainWindow.addKeyListener(keyManager);
        mainWindow.getCanvas().addMouseListener(mouseManager);
        Assets.init();
        gameState = new GameState();
        State.setState(gameState);
    }
    
    /**
     * method that updates all things, all variables(like positions...)
     */
    public void update(){
        keyManager.update();
        if(State.getState() != null) State.getState().update();
    }
    
    /**
     * method that draws on the canvas
     */
    public void render(){
        bufferStrategy = mainWindow.getCanvas().getBufferStrategy(); // check if there is already a bufferstrategy (how should i draw stuff?)
        if(bufferStrategy == null){
            mainWindow.getCanvas().createBufferStrategy(3); //3 is the number of buffers, 3 is ok
            return;
        }
        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //start drawing
        if(State.getState() != null) State.getState().render(g);
        //finishing drawing
        
        bufferStrategy.show();
        g.dispose();
   }

    @Override
    public void run() {        
        init();
        
        int  fps = 60;
        double timePerUpdate = 1000000000 / fps; //in how many milliseconds do i recall the update method
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1 ){
                update();
                render();
                ticks++;
                delta--;
            }
            
            if(timer >= 1000000000){
                if(booldebug) System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    public void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public void stop(){
        if(!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
    
    public static boolean isBooldebug(){
        return booldebug;
    }
}
