package videogame.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Emanuele Feola
 */
public class ImageLoader {
    
    /**
     * method that returns a BufferedImage that java can manage
     * @param path
     * @return 
     */
    public static BufferedImage imageLoader(String path){
        //System.out.println(path);
        try {
            return ImageIO.read(new FileInputStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
