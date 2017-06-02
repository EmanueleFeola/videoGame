package videogame.entity;

import java.awt.Graphics;
import java.awt.Image;
import videogame.entity.creatures.Creature;
import videogame.graphics.Assets;

/**
 *
 * @author Emanuele Feola
 */
public class Explosion extends Entity{
    private static int enemyWidth = 75;
    private static int enemyHeight = 75;
    private int time;
    
    public Explosion(double x, double y) {
        super(x, y, enemyWidth, enemyHeight);
        time = 0;
    }

    @Override
    public void update() {
        time ++;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((Image) Assets.getExplosion(), (int)x, (int)y, width, height, null);
    }
    
    public int getTime(){
        return time;
    }
    
}
