package sabokan.game.entities;

import java.awt.Point;
import java.io.Serializable;
import sabokan.game.Trace;

/**
 *
 * @author anaka
 */
public abstract class Positionable implements Serializable {

    protected volatile Point position;

    public Positionable(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public int getY() {
        return position.y;
    }

    public int getX() {
        return position.x;
    }

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
    
    public <T extends Positionable> boolean isOnTopOf(T other) {
        return other != null && this.position.equals(other.position);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " @ [" + position.x + ',' + position.y + ']';
    }
    
    public void log(String msg) {
        Trace.info(toString() + ' ' + msg);
    }
}
