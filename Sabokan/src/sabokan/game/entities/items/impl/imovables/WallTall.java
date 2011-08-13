package sabokan.game.entities.items.impl.imovables;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author tassos
 */
public class WallTall extends AbstractImovable{
    private static final Image texture = Constants.getResourceAsImage("Wall Block Tall.png");

    public WallTall(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}
