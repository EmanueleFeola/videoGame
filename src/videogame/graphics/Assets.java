package videogame.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Emanuele Feola
 */
public class Assets {
    //load user's ships
    private static ArrayList<BufferedImage> userShips = new ArrayList<>();
    private static int userShipsWidth = 80;
    private static int userShipsHeight = 80;
    private static int userShipsNumbers = 7;
    
    //load enemy's ships
    private ArrayList<BufferedImage> enemieShips;
    
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.imageLoader("C:\\Users\\Emanuele Feola\\Desktop\\userShips.png"));
        for(int x = 0; x < userShipsNumbers*userShipsWidth;){
            userShips.add(sheet.crop(x, 0, userShipsWidth, userShipsHeight));
            x += userShipsWidth;
        }
    }
    
    public static ArrayList getUserShips(){
        return userShips;
    }
}
