package sabokan.game.events;

import sabokan.game.utils.DialogCarrier;

/**
 * Raised when the player should initiate chatting
 * @author anaka
 */
public class DialogEvent extends Exception {
    private final DialogCarrier dialog;

    public DialogEvent(DialogCarrier dialog) {
        this.dialog = dialog;
    }

    public DialogCarrier getDialog() {
        return dialog;
    }
}
