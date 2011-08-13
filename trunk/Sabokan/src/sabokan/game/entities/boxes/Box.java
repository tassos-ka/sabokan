package sabokan.game.entities.boxes;

import java.awt.Graphics2D;
import sabokan.game.Constants;
import sabokan.game.entities.Pushable;
import sabokan.game.entities.Renderable;

/**
 * Abstract class that all Boxes should implement
 * @author anaka
 */
public abstract class Box extends Pushable implements Renderable {

    public Box(int x, int y) {
        super(x, y);
    }
    
    public abstract void update();

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY -Constants.OFFSET, null);
    }
}
