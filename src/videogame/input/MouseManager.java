package videogame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import videogame.GameModel;
import videogame.entity.creatures.Bullet;
import videogame.states.GameState;

/**
 *
 * @author Emanuele Feola
 */
public class MouseManager implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
          GameState.getBullets().add(new Bullet(GameState.getPlayer().getX(), GameState.getPlayer().getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
