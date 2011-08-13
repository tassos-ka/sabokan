package sabokan.game.entities.items.impl.collectables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author tassos
 */
public class Bug extends AbstractCollectable {

    private static final Image texture = Constants.getResourceAsImage("Enemy Bug.png");

    public Bug(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
