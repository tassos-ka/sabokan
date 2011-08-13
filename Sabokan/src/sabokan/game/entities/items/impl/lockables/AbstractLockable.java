package sabokan.game.entities.items.impl.lockables;

import sabokan.game.entities.items.Item;
import sabokan.game.entities.items.Lockable;
import sabokan.game.entities.items.impl.collectables.AbstractCollectable;
import sabokan.game.entities.players.Player;
import sabokan.game.events.CollectItemEvent;

/**
 *
 * @author anaka
 */
public abstract class AbstractLockable extends Item implements Lockable {

    private boolean isLocked = true;
    
    public AbstractLockable(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(Player player) throws CollectItemEvent {
        if (isLocked() && player != null && player.canUnlock(this)) {
            if (player.isNextTo(this)) { //is he next to me?
                //then he can collect me
                player.unlock(this);
                AbstractCollectable inside = contains();
                if (inside != null) {
                    throw new CollectItemEvent(inside);
                }
            }
        }
    
    }

    @Override
    public void unlock() {
        isLocked = false;
        open();
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void open() {
        //zzz
    }

    @Override
    public boolean isOpen() {
        return !isLocked();
    }
    
    @Override
    public boolean canWalkThrough() {
        return false;
    }
}
