package sabokan.game.entities;

import sabokan.game.entities.levels.Level;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 * Abstract class that all objects that want to be moved around by the player
 * should implement
 * @author anaka
 */
public abstract class Pushable extends Movable {

    public Pushable(int x, int y) {
        super(x, y);
    }

    /**
     * Pushes the entity to the given direction int the level.
     * Returns false if the push failed
     * @param dir
     * @param level
     * @return
     */
    public boolean push(Direction dir, Level level) {
        Point newPosition = Direction.movePosition(this.position, dir);
        if (canMove(newPosition, level)) {
            this.position = newPosition;
            return true;
        }
        return false; //push failed
    }

    /**
     * Checks whether the tile given allows moving there in the Level
     * @param p
     * @param level
     * @return
     */
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
