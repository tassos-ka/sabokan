package sabokan.game.entities.players.impl;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.players.Player;

/**
 *
 * @author anaka
 */
public class PlayerGirl extends Player {

    private static final Image texture = Constants.getResourceAsImage("Character Pink Girl.png");
    
    public PlayerGirl(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
