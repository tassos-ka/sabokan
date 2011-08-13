package sabokan.game.entities.characters;

import java.awt.Graphics2D;
import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.Positionable;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.Renderable;
import sabokan.game.events.DialogEvent;
import sabokan.game.utils.DialogCarrier;

/**
 * Abstract class that all interactable entities should implement
 * @author anaka
 */
public abstract class Char extends Positionable implements Renderable {
    
    private final static Image bubble = Constants.getResourceAsImage("SpeechBubbleSmall.png");
    
    public Char(int x, int y) {
        super(x, y);
    }
    
    protected abstract String talk();
    
    public abstract void setDialogText(String dialogText);

    public void update(Player player) throws DialogEvent {
        if (player != null) {
            if (this.isNextTo(player) && player.isTalking()) { //there he is!! start talking!
                player.log("started talking to " + toString());
                final DialogCarrier dialog = new DialogCarrier(talk());
                throw new DialogEvent(dialog); //off we go then
            }
        } else {
            //I see dead people!
            throw new UnsupportedOperationException("I do not talk to null entities... Sorry...");
        }
    }
    
    
    @Override
    public void paint(Graphics2D g) {
        //draw char
        g.drawImage(getTexture(), position.x * Constants.DX, position.y * Constants.DY - Constants.OFFSET, null);
        //draw bubble
        g.drawImage(bubble, (position.x+1) * Constants.DX, position.y * Constants.DY - Constants.DY / 2, null);
    }
}
