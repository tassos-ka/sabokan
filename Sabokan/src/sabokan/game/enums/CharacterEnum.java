package sabokan.game.enums;

import sabokan.game.entities.characters.*;
import sabokan.game.entities.characters.impl.*;

/**
 * An enumarator which contains all the Char Classes
 * @author anaka
 */
public enum CharacterEnum {
    PRINCESS(PrincessChar.class),
    CAT_GIRL(CatGirlChar.class),
    NINJA_GIRL(NinjaGirlChar.class);
    
    private final Class<? extends Char> clazz;

    private CharacterEnum(Class<? extends Char> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public Class<? extends Char> getClazz() {
        return clazz;
    }
}
