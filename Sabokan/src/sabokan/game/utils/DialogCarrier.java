package sabokan.game.utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import sabokan.game.Constants;
import sabokan.game.entities.Renderable;

/**
 * This class is responsible for displaying dialogs
 * @author anaka
 */
public class DialogCarrier implements Renderable {
    private BufferedImage texture;
    private int totalFrames;
    private int currentFrame = 0;
    private int timeBetweenFrames;

    private String text;

    /**
     * Create a new instance that will render the given text
     * @param text
     */
    public DialogCarrier(String text) {
        if (text != null && text.length() > 0) {
            this.text = text;
            this.totalFrames = text.length();
            this.timeBetweenFrames = 100;
        } else {
            throw new IllegalArgumentException("DialogCarrier should carry a dialog!");
        }
    }

    /**
     * Retrieves the text associated with this instance
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Sets teh dialog text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retrieves the texture that should be rendered
     * @return
     */
    @Override
    public Image getTexture() {
        if (texture == null) {
            throw new UnsupportedOperationException("Use prepare() first!");
        } else {
            return texture;
        }
    }

    /**
     * Prepares the texture for rendering async
     * @param dim
     * @throws InterruptedException
     */
    public void prepare(Dimension dim) throws InterruptedException {
        if (dim != null && dim.width > 0 && dim.height > 0) {
            currentFrame = 0;
            Image background = Constants.getResourceAsImage("SpeechBubble.png");
            texture = new BufferedImage(dim.width, Constants.IMAGE_HEIGHT + 30, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = texture.createGraphics();
            g2d.setPaint(Constants.DIALOG_BACKGROUND_COLOR);
            g2d.fillRect(0, 0, dim.width, Constants.IMAGE_HEIGHT + 30);
            g2d.drawImage(background, 0, 0, null);
        } else {
            throw new IllegalArgumentException("Invalid dimension!");
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(getTexture(), 0, 0, null);
   }

    /**
     * Gets the total number of frames for the rendering of the dialog
     * @return
     */
    public int getTotalFrames() {
        return totalFrames;
    }

    /**
     * Paints the next frame
     * @return
     */
    public boolean nextFrame() {
        if (currentFrame++ < totalFrames + 3) {
            paintText(Math.min(totalFrames, currentFrame));
            return true;
        }
        return false;
    }

    /**
     * Paints the text on the texture
     * @param length
     */
    private void paintText(int length) {
        String sub = text.substring(0, length);
        Graphics2D g2d = (Graphics2D)texture.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(Constants.DIALOG_FONT);
        g2d.setPaint(Constants.DIALOG_TEXT_COLOR);
        g2d.drawString(sub, Constants.IMAGE_WIDTH + 20, 50);
    }

    /**
     * Retrieves the default time in ms between frames
     * @return
     */
    public int getTimeBetweenFrames() {
        return timeBetweenFrames;
    }
}
