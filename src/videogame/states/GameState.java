package videogame.states;

import videogame.graphics.Assets;
import java.awt.Graphics;
import java.awt.Image;
import videogame.GameModel;
import videogame.entity.creatures.Player;

/**
 *
 * @author Emanuele Feola
 */
public class GameState extends State{
    private Player player;
    
    public GameState(){
        player = new Player(GameModel.getWidth()/2-40, GameModel.getHeight()-150);
    }
    
    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics g){
        player.render(g);
    }
    
}
