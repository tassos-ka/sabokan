package sabokan.game.entities.items.impl.collectables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author anaka
 */
public class GreenGem extends AbstractCollectable {

    private static final Image texture = Constants.getResourceAsImage("Gem Green.png");

    public GreenGem(int x, int y) {
        super(x, y);
    }
    
    @Override
    public Image getTexture() {
        return texture;
    }
}
