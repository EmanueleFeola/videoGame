package videogame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import videogame.GameModel;

/**
 *
 * @author Emanuele Feola
 */
public class KeyManager implements KeyListener{
    private boolean[] keys;
    private static boolean up;
    private static boolean down;
    private static boolean right;
    private static boolean left;
    
    public KeyManager(){
        keys = new boolean[100];
    }
    
    public void update(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(GameModel.isBooldebug()) System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    public static boolean isUp() {
        return up;
    }

    public static boolean isDown() {
        return down;
    }

    public static boolean isRight() {
        return right;
    }

    public static boolean isLeft() {
        return left;
    }

}
