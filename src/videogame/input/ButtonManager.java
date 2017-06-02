package videogame.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import videogame.GameModel;

/**
 *
 * @author Emanuele Feola
 */
public class ButtonManager implements ActionListener{
    GameModel m;
    JFrame window;
    
    public ButtonManager(GameModel m, JFrame window){
        this.window = window;
        this.m = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        m.start();
        window.dispose();
    }
    
}
