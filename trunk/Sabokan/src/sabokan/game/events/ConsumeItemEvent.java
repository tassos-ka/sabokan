package sabokan.game.events;

import sabokan.game.entities.items.impl.consumables.AbstractConsumable;

/**
 * Raised when the player should consume an item
 * @author anaka
 */
public class ConsumeItemEvent extends ItemEvent {

    private final AbstractConsumable item;

    public ConsumeItemEvent(AbstractConsumable item) {
        this.item = item;
    }

    public AbstractConsumable getItem() {
        return item;
    }
}
