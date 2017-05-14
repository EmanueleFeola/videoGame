package videogame.entity.creatures;

import java.awt.Graphics;
import java.awt.Image;
import videogame.graphics.Assets;
import videogame.graphics.ImageLoader;

/**
 *
 * @author Emanuele Feola
 */
public class Bullet extends Creature{
    private int bulletSpeed = 8;
    private boolean hitTarget =  false;
    private int type = 0;
    
    public Bullet(double x, double y) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        speed = bulletSpeed;
    }
    
    public Bullet(double x, double y, int i) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        speed = -bulletSpeed;
        type = i;
    }

    @Override
    public void update() {
        xMove = 0;
        yMove = -speed;
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getBullet(), (int)x, (int)y, 20, 20, null);
    }
    
    public double getX(){
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public boolean hasHitTarget(){
        return hitTarget;
    }
    
    public void setHitTarget(boolean v){
        this.hitTarget = v;
    }
}
