package sabokan.game.events;

import javax.swing.JDialog;

/**
 *
 * @author anaka
 */
public class PlayerWonEvent extends Exception {

    private final String winningMessage;
    private final Class<? extends JDialog> endingScreen;

    public PlayerWonEvent(Class<? extends JDialog> endingScreen, String message) {
        super(message);
        this.winningMessage = message;
        this.endingScreen = endingScreen;
    }

    public Class<? extends JDialog> getEndingScreen() {
        return endingScreen;
    }

    public String getWinningMessage() {
        return winningMessage;
    }
}
