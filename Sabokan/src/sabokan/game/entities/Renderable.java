package sabokan.game.entities;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * All objects that want to be painted in the game should implement this interface
 * @author anaka
 */
public interface Renderable {
    
    public Image getTexture();
    
    public void paint(Graphics2D g);
}
