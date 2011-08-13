package sabokan.game.entities.items.impl.collectables;

import java.awt.Image;
import org.junit.Test;
import static org.junit.Assert.*;
import sabokan.game.entities.players.Player;
import sabokan.game.events.CollectItemEvent;

/**
 *
 * @author tassos
 */
public class AbstractCollectableTest {

    public AbstractCollectableTest() {
    }

    /**
     * Test of canWalkThrough method, of class AbstractCollectable.
     */
    @Test
    public void testCanWalkThrough() {
        AbstractCollectable instance = new AbstractCollectableImpl();
        assertTrue(instance.canWalkThrough());
    }

    /**
     * Test of update method, of class AbstractCollectable.
     */
    @Test
    public void testUpdate() throws Exception {
        Player player = new Player(0, 100) {

            @Override
            public Image getTexture() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        AbstractCollectable instance = new AbstractCollectableImpl();
        instance.update(player);
    }

        /**
     * Test of update method, of class AbstractCollectable.
     */
    @Test(expected = CollectItemEvent.class)
    public void testUpdateTrue() throws Exception {
        Player player = new Player(0, 0) {

            @Override
            public Image getTexture() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        AbstractCollectable instance = new AbstractCollectableImpl();
        instance.update(player);
    }

    /**
     * Test of isCollected method, of class AbstractCollectable.
     */
    @Test
    public void testIsCollected() {
        AbstractCollectable instance = new AbstractCollectableImpl();
        assertFalse(instance.isCollected());
    }

    /**
     * Test of collect method, of class AbstractCollectable.
     */
    @Test
    public void testCollect() {
        AbstractCollectable instance = new AbstractCollectableImpl();
        assertFalse(instance.isCollected());
        assertEquals(instance, instance.collect());
        //subsequent calls should return nothing
        assertNull(instance.collect());
        assertTrue(instance.isCollected());
    }

    public class AbstractCollectableImpl extends AbstractCollectable {

        public AbstractCollectableImpl() {
            super(0, 0);
        }

        public AbstractCollectableImpl(int x, int y) {
            super(x, y);
        }

        @Override
        public Image getTexture() {
            return null;
        }
    }
}