package sabokan.game.events;

import javax.swing.JDialog;

/**
 * Raised when the player has succesfully won the game
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

    /**
     * A JDialog that should be opened after the finishing of the game
     */
    public Class<? extends JDialog> getEndingScreen() {
        return endingScreen;
    }

    /**
     * The message that is going to be displayed for the user
     */
    public String getWinningMessage() {
        return winningMessage;
    }
}
