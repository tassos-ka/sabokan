package sabokan.game.enums;

import sabokan.game.entities.items.*;
import sabokan.game.entities.items.impl.collectables.*;
import sabokan.game.entities.items.impl.imovables.*;
import sabokan.game.entities.items.impl.lockables.*;

/**
 * An enumarator which contains all the Item Classes
 * @author anaka
 */
public enum ItemEnum {

    BLUE_GEM(BlueGem.class),
    GREEN_GEM(GreenGem.class),
    ORANGE_GEM(OrangeGem.class),
    KEY(Key.class),
    CHEST(Chest.class),
    BUG(Bug.class),
    TALL_TREE(TallTree.class),
    UGLY_TREE(UglyTree.class),
    SHORT_WALL(WallShort.class),
    TALL_WALL(WallTall.class);
    private final Class<? extends Item> clazz;

    private ItemEnum(Class<? extends Item> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public Class<? extends Item> getClazz() {
        return clazz;
    }
}
