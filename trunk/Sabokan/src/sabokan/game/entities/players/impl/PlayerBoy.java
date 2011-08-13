package sabokan.game.entities.players.impl;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.players.Player;

/**
 *
 * @author anaka
 */
public class PlayerBoy extends Player {

    private static final Image texture = Constants.getResourceAsImage("Character Boy.png");
    
    public PlayerBoy(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
