package sabokan.game.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author anaka
 */
public abstract class Movable extends Positionable implements KeyListener {

    protected transient State state = State.IDLE;

    public Movable(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public void move(Direction dir) {
        position = Direction.movePosition(position, dir);
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        state = State.IDLE;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //i'm deaf
    }

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
