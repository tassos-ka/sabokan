package sabokan.mazes;

import org.junit.Test;

/**
 *
 * @author tassos
 */
public class MazeGeneratorTest {

    public MazeGeneratorTest() {
    }

    /**
     * Test of generateSaveMaze method, of class MazeGenerator.
     */
    @Test
    public void testGenerateSaveMaze() {
        new MazeGenerator().generateSaveMaze(100);
    }

    /**
     * Test of generateMaze method, of class MazeGenerator.
     */
    @Test
    public void testGenerateMaze() {
        new MazeGenerator().generateMaze();
    }
}
