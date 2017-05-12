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

    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {
        if(KeyManager.isUp()) y --;
        if(KeyManager.isDown()) y++;
        if(KeyManager.isRight()) x++;
        if(KeyManager.isLeft()) x--;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((Image) Assets.getUserShips().get(6), (int)x, (int)y, null);
    }
    
}
