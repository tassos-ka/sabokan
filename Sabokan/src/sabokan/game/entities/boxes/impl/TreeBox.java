package sabokan.game.entities.boxes.impl;

import java.awt.Image;
import sabokan.game.Constants;
import sabokan.game.entities.boxes.Box;

/**
 *
 * @author anaka
 */
public class TreeBox extends Box{

    private static final Image texture = Constants.getResourceAsImage("Tree Short.png");
    
    public TreeBox(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        //zzzz
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}

