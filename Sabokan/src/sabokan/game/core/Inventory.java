package sabokan.game.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import sabokan.game.core.Inventory.InventoryItem;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.items.Unique;
import sabokan.game.entities.items.Usable;
import sabokan.game.entities.items.impl.collectables.Bug;

/**
 * Inventory class
 * @author anaka
 */
public class Inventory extends HashMap<InventoryItem, Integer> {

    /**
     * How many bugs the player has to collect to win
     */
    private final int numberOfBugsToWin = 100;

    /**
     * Check the inventory for the item
     * @param item
     * @return
     */
    public boolean contains(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        return super.get(iItem) != null;
    }

    /**
     * Adds an item to the invetory
     * @param item
     */
    public void add(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        Integer exists = get(iItem);
        if (exists == null) {
            exists = 0;
        }
        super.put(iItem, ++exists);
    }

    /**
     * Removes an item from the invetory
     * @param item
     */
    public void remove(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        Integer exists = get(iItem);
        if (exists != null && exists > 1) {
            super.put(iItem, --exists);
        } else {
            super.remove(iItem);
        }
    }

    /**
     * Gets the percentage of the completed game
     * @return
     */
    public float getPercentageOfBugsCollected() {
        return 1.0f * getBugsCollected() / numberOfBugsToWin;
    }

    /**
     * Returns the number of bugs collected by the player
     * @return
     */
    public int getBugsCollected() {
        return quantityOf(new Bug(0,0));
    }

    /**
     * Returns how many instances of the specified item are found in the inventory
     * @param item
     * @return
     */
    public int quantityOf(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        return super.get(iItem) != null ? super.get(iItem).intValue() : 0;
    }

    /**
     * Returns a Set of all items currently in the invetory
     * @return
     */
    public Set<Item> getAllItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            items.add(item.item);
        }
        return items;
    }

    /**
     * Returns all items that cannot be used by the player
     * @return
     */
    public Set<Item> getNotUsableItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            if (!(item.item instanceof Usable)) {
                items.add(item.item);
            }
        }
        return items;
    }

    /**
     * Retruns all items that can be used by the player
     * @return
     */
    public Set<Item> getUsableItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            if (item.item instanceof Usable) {
                items.add(item.item);
            }
        }
        return items;
    }

    /**
     * Private class for O(1) inventory searches
     * Wraps an item and also implements equals and hashcode for
     * same classes of items. Two items are considered the same
     * if and only if they have the same Class.
     */
    protected static class InventoryItem implements Unique {

        private final Item item;

        public InventoryItem(Item item) {
            this.item = item;
        }

        @Override
        public boolean equals(Object other) {
            if (other != null && item != null) {
                if (other instanceof Item) {
                    return item.getClass().equals(other.getClass());
                } else if (other instanceof InventoryItem) {
                    InventoryItem iItem = (InventoryItem)other;
                    return iItem.item != null && item.getClass().equals(iItem.item.getClass());
                }
            }
            return other == item;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            return hash + (item != null ? item.getClass().hashCode() : 0);
        }
    }
}
