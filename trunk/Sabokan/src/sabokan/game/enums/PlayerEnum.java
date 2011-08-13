package sabokan.game.enums;

import sabokan.game.entities.players.*;
import sabokan.game.entities.players.impl.*;

/**
 * An enumarator which contains all the Player Classes
 * @author anaka
 */
public enum PlayerEnum {
    BOY(PlayerBoy.class),
    GIRL(PlayerGirl.class);
    
    private final Class<? extends Player> clazz;

    private PlayerEnum(Class<? extends Player> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public Class<? extends Player> getClazz() {
        return clazz;
    }
}
