package videogame.states;

import videogame.graphics.Assets;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import videogame.GameModel;
import videogame.entity.Explosion;
import videogame.entity.creatures.Bullet;
import videogame.entity.creatures.Creature;
import videogame.entity.creatures.Enemy;
import videogame.entity.creatures.Player;
import videogame.windows.GameOver;
import videogame.windows.StartMenu;

/**
 *
 * @author Emanuele Feola
 */
public class GameState extends State {
    private JFrame window;
    private static ArrayList<Bullet> userBullets;
    private static ArrayList<Bullet> enemiesBullets;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Explosion> explosions;
    private static Player player;
    private static int levelNumber = 1;
    private static int enemiesNumber = 0;
    private static int enemiesForRow = 5;
    private int fpsAfterGameOver = 60;
    private int contFpsAfterGameOver = 0;
    private static int points = 0;
    private static int stationHealth = 100;
    private boolean isOver = false;

    public GameState(JFrame window) {
        this.window = window;
        userBullets = new ArrayList();
        enemiesBullets = new ArrayList();
        enemies = new ArrayList();
        explosions = new ArrayList();
        initPlayer();
        initLevel(enemiesNumber, 0);
    }

    public void initPlayer() {
        player = new Player(GameModel.getWIDTH() / 2 - 40, GameModel.getHEIGHT() - 150);
    }

    public void enemiesUpdate() {
        if (!enemies.isEmpty()) {
            for (int n = enemies.size() - 1; n > -1; n--) { // Traverse the array in reverse order otherwise an esception is thrown
                //if(player.getX() >= enemies.get(n).getX() && player.getX() <= enemies.get(n).getX()+100) enemies.get(n).shoot();
                if (enemies.get(n).getY() <= GameModel.getHEIGHT() - 100 && enemies.get(n).getHealth() > 0) {
                    enemies.get(n).update();
                } else {
                    if (enemies.get(n).getHealth() <= 0) {
                        points++;
                        explosions.add(new Explosion(enemies.get(n).getX(), enemies.get(n).getY()));
                    }else{
                        GameState.setHealth(GameState.getHealth()-5);
                    }
                    enemies.remove(n);
                }
            }
        }
    }
    
    public void playerUpdate() {
        if (player.getHealth() > 0){
                    player.update();
        } else{
            explosions.add(new Explosion(player.getX(), player.getY()));
            player.setX(1000);
            player.setY(1000);
            gameOver();
        }
    }
    public void gameOver(){
        if(contFpsAfterGameOver >= fpsAfterGameOver){
                window.dispose();
                GameOver.getGameOver();
//                if(!isOver)new StartMenu();
//                isOver = true;
            }
        else contFpsAfterGameOver++;
        GameModel.writePoint();
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
        if(GameState.getHealth() > 0){
            checkCollisions();
            playerUpdate();
            enemiesUpdate();
            userBulletsUpdate();
            explosionsUpdate();
            enemiesBulletsUpdate();
            if(enemies.isEmpty()){
                enemiesNumber += enemiesForRow;
                initLevel(enemiesNumber, levelNumber);
                levelNumber++;
            }
        }else{
            gameOver();
        }
    }
    
    public void initLevel(int enemiesNumber, int level){
        if(level <= 4)initEnemiesEasy(enemiesNumber, level);
        else if(level == 5) initEnemiesMedium(10);
        else{
            this.enemiesNumber = 0;
            levelNumber = 0;
        }
    }
    
    public void initEnemiesEasy(int enemiesNumber, int type){
        int y = 0;
        for(int cont = 0; cont < enemiesNumber/enemiesForRow; cont++){
            for(int x = 100; x < enemiesForRow*50+100; ) {
                enemies.add(new Enemy((int) x, y, type));
                x += 50;
            }
            y += 50;
        }
    }
    
    public void initEnemiesMedium(int enemiesNumber){
        Random rand = new Random();
        for(int cont = 0; cont < enemiesNumber; ){
            int randomX = rand.nextInt((GameModel.getWIDTH()) + 1);
            int randomY = rand.nextInt(100 + 1);
            if(checkCoordinates(randomX, randomY)){
                enemies.add(new Enemy((int) randomX , randomY, 2));
                cont++;
            }
        }
    }
    
    public boolean checkCoordinates(int x, int y){
        for(Enemy e : enemies){
            if(e.getX() < x && e.getX() + e.getWidth() > x){
                if(e.getY() < y && e.getY() + e.getHeight() > y){
                    return false;
                }
            }
        }
        return true;
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
        for (Enemy enemy : enemies) {
        if(player.getX() >= enemy.getX() && player.getX() <= enemy.getX() + enemy.getHeight()){
                    if(player.getY() >= enemy.getY() && player.getY() <= enemy.getY() + enemy.getWidth()){
                        //player.setHealth(Player.getHealth()-1);
                        player.setHealth(0);
                    }
                }
        } 
        for (Bullet bullet : userBullets) {
            for (Enemy enemy : enemies) {
                if(bullet.getX() >= enemy.getX() && bullet.getX() <= enemy.getX() + enemy.getHeight()){
                    if(bullet.getY() >= enemy.getY() && bullet.getY() <= enemy.getY() + enemy.getWidth()){
                        bullet.setHitTarget(true);
                        enemy.setHealth(enemy.getHealth() - 5);
                    }
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
    
    public static int getPoints(){
        return points;
    }
    
    public static int getHealth(){
        return stationHealth;
    }
    
    public static void setHealth(int val){
        stationHealth = val;
    }
}
