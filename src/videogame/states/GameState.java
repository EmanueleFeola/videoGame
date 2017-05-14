package videogame.states;

import videogame.graphics.Assets;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import videogame.GameModel;
import videogame.entity.Explosion;
import videogame.entity.creatures.Bullet;
import videogame.entity.creatures.Enemy;
import videogame.entity.creatures.Player;

/**
 *
 * @author Emanuele Feola
 */
public class GameState extends State{
    private static ArrayList<Bullet> bullets;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Explosion> explosions;
    private static Player player;
    private static Enemy enemy = new Enemy(Math.random() * GameModel.getWIDTH()/2 + GameModel.getWIDTH()/4, 50, 0);;
    
    public GameState(){
        bullets = new ArrayList();
        enemies = new ArrayList();
        explosions = new ArrayList();
        //initPlayer();
        //initEnemies();
        //initLevel();
        player = new Player(GameModel.getWIDTH()/2-40, GameModel.getHEIGHT()-150);
        //non si dovrebbe fare ma finche testo le collisioni va bene (riga sotto)
        enemies.add(enemy);
    }
    
    @Override
    public void update() {
        checkCollisions();
        player.update();
        
        if(!enemies.isEmpty()){
        for(int n = enemies.size()-1; n > -1; n--){ // Traverse the array in reverse order otherwise an esception is thrown
            if(enemies.get(n).getHealth() > 0) enemies.get(n).update();
            else{
                enemies.remove(n);
                explosions.add(new Explosion(enemy.getX(), enemy.getY()));
                }
            }
        }
        
        if(!bullets.isEmpty()){
            for(int n = bullets.size()-1; n > -1; n--){ // Traverse the array in reverse order otherwise an esception is thrown
                if(bullets.get(n).getY() >= bullets.get(n).getSpeed()+1 && bullets.get(n).hasHitTarget() == false) bullets.get(n).update();
                else bullets.remove(n);
            }   
        }
        
        if(!explosions.isEmpty()){
            for(int n = explosions.size()-1; n > -1; n--){ // Traverse the array in reverse order otherwise an esception is thrown
                if(explosions.get(n).getTime() < 60) explosions.get(n).update();
                else explosions.remove(n);
            }   
        }
        
    }

    @Override
    public void render(Graphics g){
        player.render(g);
        
        for(Enemy enemy : enemies){
            enemy.render(g);
        }
        
        for(Bullet bullet : bullets){
            bullet.render(g);
        }
        
        if(!explosions.isEmpty())
        for(Explosion e : explosions){
            e.render(g);
        }
    }
    
    public void checkCollisions(){
        for(Bullet bullet : bullets){
            for(Enemy enemy : enemies){
                if(enemy.getX() + enemy.getWidth() >= bullet.getX() && enemy.getX() <= bullet.getX() + bullet.getWidth() && enemy.getY() + enemy.getHeight() >= bullet.getY() && enemy.getY() <= bullet.getY() + bullet.getY()){
                    bullet.setHitTarget(true);
                    enemy.setHealth(enemy.getHealth()-5);
                    //punteggio++;
                }
            }
        }
    }
    
    public static ArrayList getBullets(){
        return bullets;
    }
    
    public static Player getPlayer(){
        return player;
    }
}
