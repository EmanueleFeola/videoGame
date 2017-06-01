package videogame.states;

import videogame.graphics.Assets;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import videogame.GameModel;
import videogame.entity.Explosion;
import videogame.entity.creatures.Bullet;
import videogame.entity.creatures.Creature;
import videogame.entity.creatures.Enemy;
import videogame.entity.creatures.Player;

/**
 *
 * @author Emanuele Feola
 */
public class GameState extends State {
    private static ArrayList<Bullet> userBullets;
    private static ArrayList<Bullet> enemiesBullets;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Explosion> explosions;
    private static Player player;
    private static int levelNumber = 1;
    private static int enemiesNumber = 0;
    private static int enemiesForRow = 5;

    public GameState() {
        userBullets = new ArrayList();
        enemiesBullets = new ArrayList();
        enemies = new ArrayList();
        explosions = new ArrayList();
        initPlayer();
        initEnemies(enemiesNumber);
    }

    public void initPlayer() {
        player = new Player(GameModel.getWIDTH() / 2 - 40, GameModel.getHEIGHT() - 150);
    }
// full screen (set...)
    
    public void initEnemies(int enemiesNumber) {
        int y = 0;
        for(int cont = 0; cont < enemiesNumber/enemiesForRow; cont++){
            for(int x = 100; x < enemiesForRow*50+100; ) {
                enemies.add(new Enemy((int) x , y, 0));
                x += 50;
            }
            y += 50;
        }
    }

    public void enemiesUpdate() {
        if (!enemies.isEmpty()) {
            for (int n = enemies.size() - 1; n > -1; n--) { // Traverse the array in reverse order otherwise an esception is thrown
                //if(player.getX() >= enemies.get(n).getX() && player.getX() <= enemies.get(n).getX()+100) enemies.get(n).shoot();
                if (enemies.get(n).getY() <= GameModel.getHEIGHT() - 100 && enemies.get(n).getHealth() > 0) {
                    enemies.get(n).update();
                } else {
                    if (enemies.get(n).getHealth() <= 0) {
                        explosions.add(new Explosion(enemies.get(n).getX(), enemies.get(n).getY()));
                    }
                    enemies.remove(n);
                }
            }
        }
    }

    public void userBulletsUpdate() {
        if (!userBullets.isEmpty()) {
            for (int n = userBullets.size() - 1; n > -1; n--) { // Traverse the array in reverse order otherwise an esception is thrown
                if (userBullets.get(n).getY() >= userBullets.get(n).getSpeed() + 1 && userBullets.get(n).hasHitTarget() == false) {
                    userBullets.get(n).update();
                } else {
                    userBullets.remove(n);
                }
            }
        }
    }

    public void explosionsUpdate() {
        if (!explosions.isEmpty()) {
            for (int n = explosions.size() - 1; n > -1; n--) { // Traverse the array in reverse order otherwise an esception is thrown
                if (explosions.get(n).getTime() < 30) {
                    explosions.get(n).update();
                } else {
                    explosions.remove(n);
                }
            }
        }
    }

    public void enemiesBulletsUpdate() {
        if (!enemiesBullets.isEmpty()) {
            for (int n = enemiesBullets.size() - 1; n > -1; n--) { // Traverse the array in reverse order otherwise an esception is thrown
                enemiesBullets.get(n).update();
            }
        }
    }

    @Override
    public void update() {
        checkCollisions();
        player.update();
        enemiesUpdate();
        userBulletsUpdate();
        explosionsUpdate();
        enemiesBulletsUpdate();
        if(enemies.isEmpty()){
            levelNumber++;
            enemiesNumber += enemiesForRow;
            initEnemies(enemiesNumber);
        }
    }

    @Override
    public void render(Graphics g) {
        player.render(g);

        for (Enemy enemy : enemies) {
            enemy.render(g);
        }

        for (Bullet bullet : userBullets) {
            bullet.render(g);
        }

        for (Bullet bullet : enemiesBullets) {
            bullet.render(g);
        }

        if (!explosions.isEmpty()) {
            for (Explosion e : explosions) {
                e.render(g);
            }
        }
    }

    public void checkCollisions() {
        for (Bullet bullet : userBullets) {
            for (Enemy enemy : enemies) {
                if (enemy.getX() + enemy.getWidth() >= bullet.getX() && enemy.getX() <= bullet.getX() + bullet.getWidth() && enemy.getY() + enemy.getHeight() >= bullet.getY() && enemy.getY() <= bullet.getY() + bullet.getY()) {
                    bullet.setHitTarget(true);
                    enemy.setHealth(enemy.getHealth() - 5);
                    //punteggio++;
                }
            }
        }
    }

    public static ArrayList getBullets() {
        return userBullets;
    }

    public static Player getPlayer() {
        return player;
    }

    public static ArrayList<Bullet> getEnemiesBullets() {
        return enemiesBullets;
    }
    
    @Override
    public BufferedImage getBackground(){
        return Assets.getBackground();
    }
}
