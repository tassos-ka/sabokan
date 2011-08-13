package sabokan.game.entities;

import java.awt.Point;
import java.io.Serializable;
import sabokan.game.Trace;

/**
 * Default entity that complies with the game coordinate system
 * @author anaka
 */
public abstract class Positionable implements Serializable {

    /**
     * The position of the object
     */
    protected volatile Point position;

    public Positionable(int x, int y) {
        this.position = new Point(x, y);
    }

    /**
     * Retrieves the position of the object
     * @return
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Retrieves the Y coordinate of the object
     * @return
     */
    public int getY() {
        return position.y;
    }

    /**
     * Retrieves the X coordinate of the object
     * @return
     */
    public int getX() {
        return position.x;
    }

    /**
     * Returns true if the onject in next to the entity
     * @param <T>
     * @param other
     * @return
     */
    public <T extends Positionable> boolean isNextTo(T other) {
        if (other != null && other.position != null) {
            int dx = Math.abs(this.position.x) - Math.abs(other.position.x);
            int dy = Math.abs(this.position.y) - Math.abs(other.position.y);
            if ((Math.abs(dx) == 1 && Math.abs(dy) == 0)
                    || (Math.abs(dx) == 0 && Math.abs(dy) == 1)) { //one tile away
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if both objects share the same position
     * @param <T>
     * @param other
     * @return
     */
    public <T extends Positionable> boolean isOnTopOf(T other) {
        return other != null && this.position.equals(other.position);
    }

    /**
     * Formatted toString() that also displays the position
     * @return
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " @ [" + position.x + ',' + position.y + ']';
    }

    /**
     * Nice formatted logging for the object
     * @param msg
     */
    public void log(String msg) {
        Trace.info(toString() + ' ' + msg);
    }
}
