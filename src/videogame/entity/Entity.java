package videogame.entity;

import java.awt.Graphics;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Emanuele Feola
 */
public abstract class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    
    public Entity(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
