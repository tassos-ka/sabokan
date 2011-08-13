package sabokan.game.entities.items;

/**
 *
 * @author anaka
 */
public interface Lockable extends Openable {
    
    public void unlock();
    
    public boolean isLocked();
    
    public <T extends Item, Collectable> T contains();
    
    public <T extends Item, Collectable> T unlocksWith();
}
