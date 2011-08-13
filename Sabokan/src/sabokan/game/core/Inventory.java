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

    private final int numberOfBugsToWin = 100;
    
    public boolean contains(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        return super.get(iItem) != null;
    }

    public void add(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        Integer exists = get(iItem);
        if (exists == null) {
            exists = 0;
        }
        super.put(iItem, ++exists);
    }

    public void remove(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        Integer exists = get(iItem);
        if (exists != null && exists > 1) {
            super.put(iItem, --exists);
        } else {
            super.remove(iItem);
        }
    }

    public float getPercentageOfBugsCollected() {
        return 1.0f * getBugsCollected() / numberOfBugsToWin;
    }
    
    public int getBugsCollected() {
        return quantityOf(new Bug(0,0));
    }
    
    public int quantityOf(Item item) {
        InventoryItem iItem = new InventoryItem(item);
        return super.get(iItem) != null ? super.get(iItem).intValue() : 0;
    }

    public Set<Item> getAllItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            items.add(item.item);
        }
        return items;
    }
    
    public Set<Item> getNotUsableItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            if (!(item.item instanceof Usable)) {
                items.add(item.item);
            }
        }
        return items;
    }
    
    public Set<Item> getUsableItems() {
        Set<Item> items = new HashSet<Item>(this.size());
        for (InventoryItem item : super.keySet()) {
            if (item.item instanceof Usable) {
                items.add(item.item);
            }
        }
        return items;
    }

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
