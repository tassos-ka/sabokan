package sabokan.game.entities.items.impl.collectables;

import java.awt.Graphics2D;
import sabokan.game.Constants;
import sabokan.game.entities.items.Collectable;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.players.Player;
import sabokan.game.events.CollectItemEvent;

/**
 *
 * @author anaka
 */
public abstract class AbstractCollectable extends Item implements Collectable {

    private boolean collected = false;
    
    public AbstractCollectable(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canWalkThrough() {
        return true;
    }

    @Override
    public void update(Player player) throws CollectItemEvent {
        if (!isCollected() && player != null) {
            if (player.isOnTopOf(this)) { //is he sitting on me?
                //then he can collect me
                throw new CollectItemEvent(collect());
            }
        }
    }

    @Override
    public boolean isCollected() {
        return collected;
    }
    
    @Override
    public AbstractCollectable collect() {
        if (!isCollected()) {
            collected = true;
            return this;
        } else {
            return null;
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        if (!isCollected()) {
            g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
        }
    }
}
