package videogame.entity;

import java.awt.Graphics;

/**
 *
 * @author Emanuele Feola
 */
public abstract class Entity {
    protected double x;
    protected double y;
    
    public Entity(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
}
