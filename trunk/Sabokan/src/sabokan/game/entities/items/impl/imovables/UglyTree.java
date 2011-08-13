package sabokan.game.entities.items.impl.imovables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author tassos
 */
public class UglyTree extends AbstractImovable{
    private static final Image texture = Constants.getResourceAsImage("Tree Ugly.png");

    public UglyTree(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
