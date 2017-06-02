package videogame.windows;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import videogame.GameModel;

/**
 *
 * @author Emanuele Feola
 */
public class GameOver extends JFrame{
    private static GameOver gameOver = null;
    
    private JPanel panel;
    JLabel background = new JLabel(new ImageIcon("images\\bg.png")); 
    private JLabel title = new JLabel("Game Over!!");
    private JButton start = new JButton("Start");
    private JButton settings =  new JButton("Settings");
    private JButton last =  new JButton("Last");
    
    private GameOver(){
        initButtons();
        initGui();
    }
    
    public void initGui(){
        //setContentPane(background);
        setLayout(new FlowLayout());
        setTitle("VideoGame");
        setResizable(false);
        setSize(GameModel.getWIDTH(), GameModel.getHEIGHT());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void initButtons(){
        panel = new JPanel(new GridLayout(4, 1, 100, 100));
        panel.add(title);
        panel.add(start);
        panel.add(settings);
        panel.add(last);
        //background.add(panel);
        getContentPane().add(panel);
        //background.setLayout(new GridLayout(4, 4));
        //background.add(start);
        //background.add(settings);
    }
    
    public JButton getButton(){
        return start;
    }
    
    public static synchronized GameOver getGameOver(){
        if(gameOver == null) gameOver = new GameOver();
        return gameOver;
    }
}
