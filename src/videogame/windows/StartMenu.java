package videogame.windows;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import videogame.GameModel;

/**
 *
 * @author Emanuele Feola
 */
public class StartMenu extends JFrame{
    private JPanel panel;
    //JLabel background = new JLabel(new ImageIcon("images\\bg.png")); 
    private JLabel title = new JLabel("PRESS START TO PLAY");
    private JTextField nameField = new JTextField("PlayerName");
    private JButton start = new JButton("Start");
    private JButton ranking =  new JButton("Ranking");
    private JButton exit =  new JButton("Exit");
    private String name;
    
    public StartMenu(){
        initButtons();
        initGui();
    }
    
    public void initGui(){
        setLayout(new FlowLayout());
        //getContentPane().setBackground(Color.black);
        setTitle("VideoGame");
        setResizable(false);
        setSize(GameModel.getWIDTH(), GameModel.getHEIGHT());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void initButtons(){
        start.setActionCommand("start");
        ranking.setActionCommand("ranking");
        exit.setActionCommand("exit");
        panel = new JPanel(new GridLayout(5, 1, 100, 100));
        //panel.setBackground(Color.black);
        panel.add(title);
        panel.add(nameField);
        panel.add(start);
        panel.add(ranking);
        panel.add(exit);
        getContentPane().add(panel);
    }
    
    public JButton getStartButton(){
        return start;
    }
    
    public JButton getRankingButton(){
        return ranking;
    }
    
    public JButton getExitButton(){
        return exit;
    }
    
    public JTextField getNameField(){
        return nameField;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
