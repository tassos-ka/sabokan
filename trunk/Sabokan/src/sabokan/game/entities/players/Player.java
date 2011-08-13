package sabokan.game.entities.players;

import java.awt.Graphics2D;
import java.awt.Point;
import sabokan.game.Constants;
import sabokan.game.core.Inventory;
import sabokan.game.entities.levels.Level;
import sabokan.game.entities.Movable;
import sabokan.game.entities.Renderable;
import sabokan.game.entities.items.Lockable;
import sabokan.game.events.FinishedLevelEvent;
import sabokan.game.events.PlayerDiedEvent;
import sabokan.game.events.PlayerWonEvent;

/**
 *
 * @author anaka
 */
public abstract class Player extends Movable implements Renderable {
    
    private transient Inventory inventory;
    protected transient int moves = Constants.DEFAULT_MOVES;
    
    public Player(int x, int y) {
        super(x, y);
        log("spawned!");
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public void update(Level level) throws FinishedLevelEvent, PlayerDiedEvent, PlayerWonEvent {
        
        if (state != null && state.shouldUpdatePosition()) { //needs to update position
            updatePosition(level);
        }
        checkIsDead();
        checkFinishedLevel(level);
        checkHasWon();
    }
    
    private void checkIsDead() throws PlayerDiedEvent {
        if (getMovesLeft() < 0) { //sorry, you died
            log("died!");
            throw new PlayerDiedEvent();
        }
    }
    
    private void checkFinishedLevel(Level level) throws FinishedLevelEvent {
        if (level.hasWon(position)) {
            log("finished Level "+ level.getClass().getSimpleName() +'!');
            throw new FinishedLevelEvent();
        }
    }
    
    private void checkHasWon() throws PlayerWonEvent {
        if (inventory.getPercentageOfBugsCollected() > 1.0) { //sorry, you died
            log("won the Game! Congratulations");
            throw new PlayerWonEvent(null, "Congratulations! You have collected all the bugs!\nSystem will now exit.");
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
    }
    
    public boolean canUnlock(Lockable locked) {
        return locked != null && inventory.contains(locked.unlocksWith());
    }
    
    public void unlock(Lockable locked) {
        if (canUnlock(locked)) {
            log("unlocks " + locked.toString() + " with a " + locked.unlocksWith().getClass().getSimpleName());
            locked.unlock();
            inventory.remove(locked.unlocksWith());
        } else {
            log("needs a " + locked.unlocksWith().getClass().getSimpleName() + "to unlock " + locked.toString());
        }
    }
    
    public boolean isTalking() {
        return state == State.TALKING;
    }
    
    public float getMovesLeft() {
        return moves * 1.0f / Constants.DEFAULT_MOVES;
    }
    
    private void updatePosition(Level level) {
        Point oldPosition = this.position;
        updatePosition(); //move there and see what happens
        if (level.isBoxThere(this.position) != null) { //we are sitting on a box
            if (level.isBoxThere(this.position).push(this.state.getMomentum(), level)) {
                log("pushed the Box " + this.state.getMomentum().name() + '!');
                moves--;
                return;
            } else {
                this.position = oldPosition;
                log("failed to push the Box " + this.state.getMomentum().name() + '!');
            }
        } else if (level.isWalkable(this.position)) {
            log("moved " + this.state.getMomentum().name() + '!');
            moves--;
        } else {
            this.position = oldPosition;
            log("failed to move " + this.state.getMomentum().name() + '!');
        }
    }
}
