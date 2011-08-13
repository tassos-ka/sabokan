package sabokan.game.entities;

import java.awt.Component;
import sabokan.game.entities.Direction;
import java.awt.Point;
import java.awt.event.KeyEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tassos
 */
public class MovableTest {

    public MovableTest() {
    }

    /**
     * Test of moveUp method, of class Movable.
     */
    @Test
    public void testMoveUp() {
        Movable underTest = new MovableImpl(0,0);
        underTest.moveUp();
        assertEquals(underTest.getPosition(), new Point(0, -1));
    }

    /**
     * Test of moveDown method, of class Movable.
     */
    @Test
    public void testMoveDown() {
        Movable underTest = new MovableImpl(0,0);
        underTest.moveDown();
        assertEquals(underTest.getPosition(), new Point(0, 1));
    }

    /**
     * Test of moveLeft method, of class Movable.
     */
    @Test
    public void testMoveLeft() {
        Movable underTest = new MovableImpl(0,0);
        underTest.moveLeft();
        assertEquals(underTest.getPosition(), new Point(-1, 0));
    }

    /**
     * Test of moveRight method, of class Movable.
     */
    @Test
    public void testMoveRight() {
        Movable underTest = new MovableImpl(0,0);
        underTest.moveRight();
        assertEquals(underTest.getPosition(), new Point(1, 0));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testMoveFromNullPosition() {
        Movable underTest = new MovableImpl(0,0);
        underTest.position = null;
        underTest.moveUp();
    }
    /**
     * Test of move method, of class Movable.
     */
    @Test
    public void testMove() {
        Movable underTest = new MovableImpl(0,0);
        for (Direction dir : Direction.values()) {
            underTest.move(dir);
        }

        //should be at the same spot
        assertEquals(underTest.getPosition(), new Point(0, 0));
    }

    /**
     * Test of keyPressed method, of class Movable.
     */
    @Test
    public void testKeyPressed() {
        Movable underTest = new MovableImpl(0,0);
        underTest.keyPressed(null);//null test
        //all keys pressed test
        Component dummyComponent = new Component() {};
        KeyEvent event = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED, 0, 0, 0, 'a');
        for (int i = KeyEvent.VK_UNDEFINED; i < KeyEvent.VK_BEGIN; i++) {
            event.setKeyCode(i);
            underTest.keyPressed(event);
            underTest.updatePosition();
        }
    }

    /**
     * Test of keyReleased method, of class Movable.
     */
    @Test
    public void testKeyReleased() {
        new MovableImpl(0,0).keyReleased(null);
    }

    /**
     * Test of keyTyped method, of class Movable.
     */
    @Test
    public void testKeyTyped() {
        new MovableImpl(0,0).keyTyped(null);
    }

    public class MovableImpl extends Movable {

        public MovableImpl(int x, int y) {
            super(x, y);
        }
    }
}