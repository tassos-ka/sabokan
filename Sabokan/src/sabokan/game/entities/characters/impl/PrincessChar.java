package sabokan.game.entities.characters.impl;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.characters.Char;

/**
 *
 * @author anaka
 */
public class PrincessChar extends Char {
    
    private static final Image texture = Constants.getResourceAsImage("Character Princess Girl.png");
    private String dialogText = "Uhmmm, I don't know";
    
    public PrincessChar(int x, int y) {
        super(x, y);
    }

    public PrincessChar(int x, int y, String dialogText) {
        super(x, y);
        this.dialogText = dialogText;
    }

    @Override
    protected String talk() {
        return dialogText;
    }
    @Override
    public Image getTexture() {
        return texture;
    }

    @Override
    public void setDialogText(String dialogText) {
        this.dialogText = dialogText;
    }
}
