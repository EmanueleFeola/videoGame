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
    private static int playerWidth = 50;
    private static int playerHeight = 50;
    private static int health;
        
    public Player(double x, double y) {
        super(x, y, playerWidth, playerHeight);
        health = Creature.DEFAULT_HEALTH;
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
    
    public void setHealth(int h){
        health = h;
    }
    
    public static int getHealth(){
        return health;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
}
