package videogame.windows;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import videogame.GameModel;
import videogame.states.GameState;

/**
 *
 * @author Emanuele Feola
 */
public class GameOver extends JFrame implements ActionListener{
    private static GameOver gameOver = null;
    
    private JPanel panel;
    JLabel background = new JLabel(new ImageIcon("images\\bg.png")); 
    private JLabel title = new JLabel("Game Over!!");
    private JLabel points = new JLabel("Il tuo punteggio: " + GameState.getPoints());
    private JButton ranking =  new JButton("Ranking");
    private JButton exit =  new JButton("Exit");
    
    private GameOver(){
        initButtons();
        initGui();
    }
    
    public void initGui(){
        setLayout(new FlowLayout());
        setTitle("VideoGame");
        setResizable(false);
        setSize(GameModel.getWIDTH(), GameModel.getHEIGHT());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void initButtons(){
        ranking.addActionListener(this);
        ranking.setActionCommand("ranking");
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        panel = new JPanel(new GridLayout(4, 1, 100, 100));
        panel.add(title);
        panel.add(points);
        panel.add(ranking);
        panel.add(exit);
        getContentPane().add(panel);
    }
    
    public static synchronized GameOver getGameOver(){
        if(gameOver == null) gameOver = new GameOver();
        return gameOver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "ranking");
        else if(command == "exit"){
            this.dispose();
            System.exit(0);
        }
    }
}
