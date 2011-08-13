package sabokan.game.entities.items.impl.imovables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author tassos
 */
public class TallTree extends AbstractImovable{

    private static final Image texture = Constants.getResourceAsImage("Tree Tall.png");

    public TallTree(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
