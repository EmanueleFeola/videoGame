package videogame.entity.creatures;

import java.awt.Graphics;
import java.awt.Image;
import videogame.graphics.Assets;
import videogame.input.KeyManager;

/**
 *
 * @author Emanuele Feola
 */
public class Player extends Creature{
    private int health;
        
    public Player(double x, double y) {
        super(x, y, 50, 50);
        health = DEFAULT_HEALTH;
    }

    @Override
    public void update() {
        getInput();
        move();
    }
    
    public void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(KeyManager.isUp()) yMove = -speed;
        if(KeyManager.isDown()) yMove = speed;
        if(KeyManager.isRight()) xMove = speed;
        if(KeyManager.isLeft()) xMove = -speed;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage((Image) Assets.getUserShips().get(6), (int)x, (int)y, width, height, null);
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
