package sabokan.game.events;

import sabokan.game.entities.items.impl.collectables.AbstractCollectable;

/**
 * Raised when the player should collect an item
 * @author tassos
 */
public class CollectItemEvent extends ItemEvent {

    private final AbstractCollectable item;

    public CollectItemEvent(AbstractCollectable item) {
        this.item = item;
    }
    
    public AbstractCollectable getItem() {
        return item;
    }
}
