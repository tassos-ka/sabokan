package sabokan.game.entities.items.impl.imovables;

import java.awt.Graphics2D;
import sabokan.game.Constants;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.players.Player;

/**
 *
 * @author tassos
 */
public abstract class AbstractImovable extends Item {

    public AbstractImovable(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(Player player) {
        //get out of here
    }


    @Override
    public void paint(Graphics2D g) {
        g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }
}


