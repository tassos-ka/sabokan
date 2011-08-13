package sabokan.game.entities.items;

/**
 *
 * @author anaka
 */
public interface Collectable {
    
    public <T extends Collectable> T collect();
    
    public boolean isCollected();
}
