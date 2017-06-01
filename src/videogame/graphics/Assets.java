package videogame.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Emanuele Feola
 */
public class Assets {
    //load background
    private static BufferedImage background;
    
    
    //load user's ships
    private static ArrayList<BufferedImage> userShips = new ArrayList<>();
    private static int userShipsWidthPixel = 80;
    private static int userShipsHeightPixel = 80;
    private static int userShipsNumbers = 7;
    
    //load bullets
    private static BufferedImage bullet;
            
    //load enemy's ships
    private static ArrayList<BufferedImage> enemiesShips = new ArrayList<>();
    private static int enemyShipsWidthPixel = 15;
    private static int enemyShipsHeightPixel = 17;
    private static int enemyShipsNumbers = 8; 
    
    //load explosion
    private static BufferedImage explosion;
    
    public static void init(){
        bullet = ImageLoader.imageLoader("C:\\Work\\images\\c.png");
        explosion = ImageLoader.imageLoader("C:\\Work\\images\\ex.png");
        background = ImageLoader.imageLoader("C:\\Work\\images\\bg.png");
        
        SpriteSheet userShipsImage = new SpriteSheet(ImageLoader.imageLoader("C:\\Work\\images\\userShips.png"));
        for(int x = 0; x < userShipsNumbers*userShipsWidthPixel;){
            userShips.add(userShipsImage.crop(x, 0, userShipsWidthPixel, userShipsHeightPixel));
            x += userShipsWidthPixel;
        }
                
        SpriteSheet enemyShipsImage = new SpriteSheet(ImageLoader.imageLoader("C:\\Work\\images\\enemyes.png"));
        for(int x = 0; x < enemyShipsNumbers*enemyShipsWidthPixel;){
            enemiesShips.add(enemyShipsImage.crop(x, 0, enemyShipsWidthPixel, enemyShipsHeightPixel));
            x += enemyShipsWidthPixel;
        }
    }
    
    public static ArrayList getUserShips(){
        return userShips;
    }
    
    public static BufferedImage getBullet(){
        return bullet;
    }
    
    public static BufferedImage getExplosion(){
        return explosion;
    }
    
    public static BufferedImage getBackground(){
        return background;
    }
    
    public static ArrayList getEnemyShips(){
        return enemiesShips;
    }
}
