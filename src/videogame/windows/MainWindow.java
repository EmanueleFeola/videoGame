package videogame.windows;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Emanuele Feola
 * @version 1.1, 2017-05-04
 */
public class MainWindow extends JFrame{
    private int width;
    private int height; 
    private Canvas canvas;
    
    public MainWindow(int width, int height){
        setTitle("VideoGame");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        canvas = new Canvas();
        canvas.setSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        getContentPane().add(canvas);
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return this;
    }
}
