package videogame.entity.creatures;

import java.awt.Graphics;
import java.awt.Image;
import videogame.GameModel;
import videogame.graphics.Assets;
import videogame.states.GameState;

/**
 *
 * @author Emanuele Feola
 */
public class Enemy extends Creature{
    private static int enemyWidth = 50;
    private static int enemyHeight = 50;
    private int type;
    private int health;
    
    public Enemy(double x, double y, int type) {
        super(x, y, enemyWidth, enemyHeight);
        this.type = type;
        if(type == 1 || type == 3){
            health = 10;
            speed = 2;
        }
        if(type == 2 || type == 4){
            health = 5;
            speed = 3;
        }
    }

    public int getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }
    
    @Override
    public void update() {
        xMove = 0;
        yMove = speed;
        moveEnemy();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((Image) Assets.getEnemyShips().get(type-1), (int)x, (int)y, width, height, null);
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

    public static int getEnemyWidth() {
        return enemyWidth;
    }

    public static int getEnemyHeight() {
        return enemyHeight;
    }

    public void shoot() {
        GameState.getEnemiesBullets().add(new Bullet(x, y, 1));
    }
}
