package sabokan.game.entities;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author anaka
 */
public interface Renderable {
    
    public Image getTexture();
    
    public void paint(Graphics2D g);
}
