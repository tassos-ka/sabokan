package sabokan.game.entities.items.impl.collectables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author anaka
 */
public class BlueGem extends AbstractCollectable {

    private static final Image texture = Constants.getResourceAsImage("Gem Blue.png");

    public BlueGem(int x, int y) {
        super(x, y);
    }
    
    @Override
    public Image getTexture() {
        return texture;
    }
}
