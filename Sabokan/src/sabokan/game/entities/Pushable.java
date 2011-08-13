package sabokan.game.entities;

import sabokan.game.entities.levels.Level;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author anaka
 */
public abstract class Pushable extends Movable {

    public Pushable(int x, int y) {
        super(x, y);
    }
    
    public boolean push(Direction dir, Level level) {
        Point newPosition = Direction.movePosition(this.position, dir);
        if (canMove(newPosition, level)) {
            this.position = newPosition;
            return true;
        }
        return false; //push failed
    }

    public boolean canMove(Point p, Level level) {
        if (level != null && p != null) {
            return level.isFree(p);
        }
        return false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
