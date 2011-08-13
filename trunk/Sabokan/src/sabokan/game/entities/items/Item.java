package sabokan.game.entities.items;

import java.awt.Graphics2D;
import sabokan.game.Constants;
import sabokan.game.entities.Positionable;
import sabokan.game.entities.Renderable;
import sabokan.game.entities.players.Player;
import sabokan.game.events.ItemEvent;

/**
 *
 * @author anaka
 */
public abstract class Item extends Positionable implements Renderable {

    public Item(int x, int y) {
        super(x, y);
    }
    
    public abstract void update(Player player) throws ItemEvent;
    
    public abstract boolean canWalkThrough();
    
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
    }
}
