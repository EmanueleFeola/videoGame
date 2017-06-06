package videogame.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import videogame.GameModel;
import videogame.windows.StartMenu;

/**
 *
 * @author Emanuele Feola
 */
public class ButtonManager implements ActionListener{
    GameModel model;
    JFrame window;
    
    public ButtonManager(GameModel m, JFrame window){
        this.window = window;
        this.model = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "start"){
            if(((StartMenu)window).getNameField().getText().length() > 0){
                ((StartMenu)window).setName(((StartMenu)window).getNameField().getText());
                model.start();
                window.dispose();
            }
        }
        else if(command == "ranking") System.out.println("ranking");
        else if(command == "exit"){
            System.out.println("exit");
            window.dispose();
        }
    }
    
}
