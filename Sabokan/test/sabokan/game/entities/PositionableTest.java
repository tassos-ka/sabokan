package sabokan.game.entities;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tassos
 */
public class PositionableTest {

    public PositionableTest() {
    }

    /**
     * Test of getPosition method, of class Positionable.
     */
    @Test
    public void testGetPosition() {
        Positionable instance = new PositionableImpl();
        Point expResult = new Point(0,0);
        Point result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Positionable.
     */
    @Test
    public void testGetY() {
        Positionable instance = new PositionableImpl();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Positionable.
     */
    @Test
    public void testGetX() {
        Positionable instance = new PositionableImpl();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of isNextTo method, of class Positionable.
     */
    @Test
    public void testIsNextTo() {
        Positionable instance = new PositionableImpl(0, 0);
        assertTrue(instance.isNextTo(new PositionableImpl(1, 0)));
        assertFalse(instance.isNextTo(new PositionableImpl(14, 0)));
    }

    /**
     * Test of isOnTopOf method, of class Positionable.
     */
    @Test
    public void testIsOnTopOf() {
        Positionable instance = new PositionableImpl(0, 0);
        assertTrue(instance.isOnTopOf(instance));
        assertFalse(instance.isOnTopOf(new PositionableImpl(1, 0)));
    }

    /**
     * Test of toString method, of class Positionable.
     */
    @Test
    public void testToString() {
        Positionable instance = new PositionableImpl(0, 0);
        instance.toString();
    }

    /**
     * Test of log method, of class Positionable.
     */
    @Test
    public void testLog() {
        Positionable instance = new PositionableImpl();
        instance.log(""); //empty string
        instance.log(null);
    }

    public class PositionableImpl extends Positionable {

        public PositionableImpl() {
            super(0, 0);
        }

        public PositionableImpl(int x, int y) {
            super(x, y);
        }
    }

}