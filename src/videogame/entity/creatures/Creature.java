package videogame.entity.creatures;

import java.awt.Graphics;
import videogame.entity.Entity;

/**
 *
 * @author Emanuele Feola
 */
public abstract class Creature extends Entity{
    protected int health;

    public Creature(double x, double y) {
        super(x, y);
        health = 10;
    }    
}
