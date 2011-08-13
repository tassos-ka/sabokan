package sabokan.game.entities.items.impl.consumables;

import java.awt.Graphics2D;
import sabokan.game.Constants;
import sabokan.game.entities.items.Consumable;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.players.Player;
import sabokan.game.events.ConsumeItemEvent;

/**
 *
 * @author anaka
 */
public abstract class AbstractConsumable extends Item implements Consumable {

    private boolean consumed = false;
    
    public AbstractConsumable(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(Player player) throws ConsumeItemEvent {
        if (!isConsumed() && player != null) {
            if (player.isOnTopOf(this)) { //is he sitting on me?
                //then he can comsume me
                consume();
                throw new ConsumeItemEvent(this);
            }
        }
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }
    
    @Override
    public void consume() {
        if (!isConsumed()) {
            consumed = true;
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        if (!isConsumed()) {
            g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
        }
    }
}

