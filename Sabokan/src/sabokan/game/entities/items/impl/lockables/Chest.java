package sabokan.game.entities.items.impl.lockables;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.items.impl.collectables.AbstractCollectable;
import sabokan.game.entities.items.impl.collectables.Key;
import sabokan.game.entities.items.impl.collectables.OrangeGem;

/**
 *
 * @author anaka
 */
public class Chest extends AbstractLockable {

    private static final Image lockedTexture = Constants.getResourceAsImage("Chest Closed.png");
    private static final Image unlockedTexture = Constants.getResourceAsImage("Chest Open.png");

    public Chest(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getTexture() {
        return isLocked() ? lockedTexture : unlockedTexture;
    }

    @Override
    public AbstractCollectable contains() {
        return new OrangeGem(position.x, position.y);
    }

    @Override
    public Key unlocksWith() {
        return new Key(0, 0);
    }
}
