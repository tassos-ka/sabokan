package sabokan.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JPanel;
import sabokan.game.core.SabokanGame;

/**
 * The main Panel where the Game is COncerned
 * This Panel is IDE friendly, feel free to drag and drop it in
 * Netbeans and see what happens
 * @author anaka
 */
public class GamePanel extends JPanel {

    /**
     * The actual game instance
     */
    private SabokanGame game;

    public GamePanel() {
        initGamePanel();
    }

    public GamePanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        initGamePanel();
    }

    public GamePanel(LayoutManager layout) {
        super(layout);
        initGamePanel();
    }

    public GamePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        initGamePanel();
    }

    /**
     * Constructs the game
     */
    private void initGamePanel() {
        game = new SabokanGame(this);
        this.setFocusable(true);
    }

    /**
     * Delegates to the games KeyListener
     * @return 
     */
    public KeyListener getGameKeylistener() {
        return game.getKeyListener();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g instanceof Graphics2D) {
            game.paintGame((Graphics2D) g);
        } else {
            throw new UnsupportedOperationException("Gameboy is not supported yet");
        }
    }

    /**
     * Resets the current Level
     */
    public void reset() {
        initGamePanel();
    }

    /**
     * Attempts to Load the specified file as Level
     * @param selectedFile 
     */
    public void loadLevel(File selectedFile) {
        game.loadLevel(selectedFile);
    }
    
    /**
     * Set minimum and Preferred size in order to the given Dimension
     * @param dim 
     */
    public void fitToLevelSize(Dimension dim) {
        setMinimumSize(dim);
        setPreferredSize(dim);
    }
}
