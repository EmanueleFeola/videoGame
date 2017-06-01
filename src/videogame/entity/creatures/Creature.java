package videogame.entity.creatures;

import java.awt.Graphics;
import videogame.GameModel;
import videogame.entity.Entity;

/**
 *
 * @author Emanuele Feola
 */
public abstract class Creature extends Entity{
    public static final int DEFAULT_SPEED = 6;
    public static final int DEFAULT_CREATURE_WIDTH = 75;
    public static final int DEFAULT_CREATURE_HEIGHT = 75;
    public static final int DEFAULT_HEALTH = 10;
    private int countEnemyMove = 0;
    int enemyMoveDelta = 15;

    protected int speed;
    protected int xMove;
    protected int yMove;
    
    public Creature(double x, double y, int width, int height) {
        super(x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }    
    
    public void move(){
        if(x + width + xMove <= GameModel.getWIDTH() && x + xMove >= 0) x += xMove;
        if(y + yMove >= 0 && y + 90 + yMove <= GameModel.getHEIGHT()) y += yMove;
    }
    
    public void moveEnemy(){
        int countX = countEnemyMove % 30;
        
        if(x + width + xMove <= GameModel.getWIDTH() && x + xMove >= 0){
            if(countX == 0){
                x += xMove + enemyMoveDelta;
                enemyMoveDelta = -enemyMoveDelta;
            } 
            else x += xMove;
        } 
        
        if(y + yMove >= 0 && y + 90 + yMove <= GameModel.getHEIGHT()) y += yMove;
        countEnemyMove += 1;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
