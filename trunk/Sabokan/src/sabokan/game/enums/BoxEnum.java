package sabokan.game.enums;

import sabokan.game.entities.boxes.*;
import sabokan.game.entities.boxes.impl.*;

/**
 *
 * @author anaka
 */
public enum BoxEnum {

    ROCK(RockBox.class),
    TREE(TreeBox.class);
    private final Class<? extends Box> clazz;

    private BoxEnum(Class<? extends Box> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public Class<? extends Box> getClazz() {
        return clazz;
    }
}
