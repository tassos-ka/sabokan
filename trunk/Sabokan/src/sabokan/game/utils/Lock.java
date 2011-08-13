package sabokan.game.utils;

/**
 * Dummy class, used for pause.
 * I know I could use the java.concurrent.Lock, but hey,
 * this adds complexity!
 * @author anaka
 */
public class Lock {

    private volatile boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        this.locked = true;
    }
    
    public void unlock() {
        this.locked = false;
    }
}
