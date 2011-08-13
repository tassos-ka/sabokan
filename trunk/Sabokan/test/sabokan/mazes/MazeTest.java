package sabokan.mazes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tassos
 */
public class MazeTest {

    public MazeTest() {
    }


    /**
     * Test of isStart method, of class Maze.
     */
    @Test
    public void testIsStart() {
        Maze instance = new Maze();
        int x = instance.getStartX();
        int y = instance.getStartY();
        assertTrue(instance.isStart(x, y));
    }

    /**
     * Test of visitPath method, of class Maze.
     */
    @Test
    public void testVisitPath() {
        Maze instance = new Maze();
        instance.visitPath(0, 0);
        instance.visitPath(-1, -1);
    }

    /**
     * Test of toString method, of class Maze.
     */
    @Test
    public void testToString() {
        Maze instance = new Maze();
        instance.toString();
    }
}