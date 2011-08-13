package sabokan.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JPanel;
import sabokan.game.core.SokobanLevelEditor;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.tiles.Tile;

/**
 * The main Editor Panel
 * @author anaka
 */
public class EditorPanel extends JPanel {

    private SokobanLevelEditor editor;
    
    public EditorPanel() {
        initEditorPanel();
    }

    public EditorPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        initEditorPanel();
    }

    public EditorPanel(LayoutManager layout) {
        super(layout);
        initEditorPanel();
    }

    public EditorPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        initEditorPanel();
    }
    
    
    private void initEditorPanel() {
        editor = new SokobanLevelEditor(this);
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g instanceof Graphics2D) {
            editor.paintEditor((Graphics2D) g);
        } else {
            throw new UnsupportedOperationException("Gameboy is not supported yet");
        }
    }
    
    public void reset() {
        initEditorPanel();
    }

    public void setTile(Tile tile) {
        editor.getLevel().setTileAt(editor.getLevel().getSelected(), tile);
        repaint();
    }

    public void setPlayer(Player player) {
        editor.setPlayer(player);
        repaint();
    }

    public void addItem(Item item) {
        editor.getLevel().addItem(item);
        repaint();
    }

    public void addCharacter(Char character) {
        editor.getLevel().addCharacter(character);
        repaint();
    }

    public void addBox(Box box) {
        editor.getLevel().addBox(box);
        repaint();
    }
    
    public void setSelectedTile(MouseEvent evt) {
        Point p = new Point(evt.getPoint().x / Constants.DX, evt.getPoint().y / Constants.DY);
        editor.getLevel().selectTile(p);
        repaint();
    }
    
    public Point getSelected() {
        return editor.getLevel().getSelected();
    }
    
    public void saveLevel(File file) {
        editor.getLevel().writeToFile(file);
    }
    
    public void loadLevel(File file) {
        editor.getLevel().loadFromFile(file);
    }
}
