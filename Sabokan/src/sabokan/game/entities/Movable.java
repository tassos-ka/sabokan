package sabokan.game.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Abstract class that defines an object able to move inside the canvas of the game
 * @author anaka
 */
public abstract class Movable extends Positionable implements KeyListener {

    /**
     * Current state of the movealbe object
     */
    protected transient State state = State.IDLE;

    /**
     * Default constructor
     * @param x
     * @param y
     */
    public Movable(int x, int y) {
        super(x, y);
    }

    /**
     * Moves up one tile
     */
    public void moveUp() {
        move(Direction.UP);
    }

    /**
     * Moves down one tile
     */
    public void moveDown() {
        move(Direction.DOWN);
    }

    /**
     * Moves left one tile
     */
    public void moveLeft() {
        move(Direction.LEFT);
    }

    /**
     * Moves right one tile
     */
    public void moveRight() {
        move(Direction.RIGHT);
    }

    /**
     * Moves to the given direction
     * @param dir
     */
    public void move(Direction dir) {
        position = Direction.movePosition(position, dir);
    }

    /**
     * Updates the position based on the state of the movable object
     */
    protected void updatePosition() {
        switch (state) {
            case MOVING_UP:
                moveUp();
                break;
            case MOVING_DOWN:
                moveDown();
                break;
            case MOVING_LEFT:
                moveLeft();
                break;
            case MOVING_RIGHT:
                moveRight();
                break;
            default:
            //do nothing?
        }
    }

    /**
     * Listens to the keyboard and updates the state of the object
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    state = State.MOVING_UP;
                    break;
                case KeyEvent.VK_DOWN:
                    state = State.MOVING_DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    state = State.MOVING_LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    state = State.MOVING_RIGHT;
                    break;
                case KeyEvent.VK_SPACE:
                    state = State.TALKING;
                    break;
                default:
                    state = State.IDLE;
            }
        }
    }

    /**
     * Sets the state to IDLE
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        state = State.IDLE;
    }

    /**
     * This does nothing
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //i'm deaf
    }

    /**
     * The states that a movable object can have
     */
    public static enum State {

        IDLE(null),
        MOVING_UP(Direction.UP),
        MOVING_RIGHT(Direction.RIGHT),
        MOVING_LEFT(Direction.LEFT),
        MOVING_DOWN(Direction.DOWN),
        TALKING(null);
        private final Direction dir;

        private State(Direction dir) {
            this.dir = dir;
        }

        public boolean shouldUpdatePosition() {
            return dir != null;
        }

        public Direction getMomentum() {
            return dir;
        }
    }
}
