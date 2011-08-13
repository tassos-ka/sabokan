package sabokan.game.entities.items.impl.collectables;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.items.Unique;
import sabokan.game.entities.items.Usable;

/**
 *
 * @author anaka
 */
public class Key extends AbstractCollectable implements Unique, Usable {

    private static final Image texture = Constants.getResourceAsImage("Key.png");

    public Key(int x, int y) {
        super(x, y);
    }
    
    @Override
    public Image getTexture() {
        return texture;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && getClass().equals(other.getClass());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash + getClass().hashCode();
    }

    @Override
    public boolean isFirstSlot() {
        return true;
    }
}
