package sabokan.game.entities.items;

/**
 *
 * @author tassos
 */
public interface Container {
    public <T extends Item, Collectable> T contains();
}
