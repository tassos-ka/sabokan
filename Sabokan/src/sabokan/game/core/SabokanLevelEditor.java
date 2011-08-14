package sabokan.game.core;

import java.awt.Graphics2D;
import java.util.List;
import sabokan.game.Constants;
import sabokan.game.EditorPanel;
import sabokan.game.Trace;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.levels.EditableLevel;
import sabokan.game.entities.players.Player;

/**
 *
 * @author anaka
 */
public class SabokanLevelEditor {

    private Player player;
    private List<Box> boxes;
    private List<Char> characters;
    private List<Item> items;
    private EditableLevel level;
    private final EditorPanel container;

    public SabokanLevelEditor(EditorPanel container) {
        if (container != null) {
            Trace.info("Started");
            this.container = container;
            Constants.initImageCache(container);
            initComponents();
        } else {
            throw new UnsupportedOperationException("Unable to initialize without defining a container frame!");
        }
        Trace.info("Game initialized");
    }

    private void initComponents() {
        level = loadLevel();
        Trace.info("Level initialized");
        player = level.initPlayer();
        Trace.info("Player initialized");
        boxes = level.initBoxes();
        Trace.info("Boxes initialized");
        characters = level.initCharacters();
        Trace.info("Characters initialized");
        items = level.initItems();
        Trace.info("Items initialized");
        repaint();
    }

    private EditableLevel loadLevel() {
        return new EditableLevel() {
        };
    }

    private void repaint() {
        container.repaint();
    }

    public void paintEditor(Graphics2D g) {
        level.paint(g);

        for (Item item : items) {
            item.paint(g);
        }

        for (Box box : boxes) {
            box.paint(g);
        }

        for (Char aChar : characters) {
            aChar.paint(g);
        }

        player.paint(g);
    }

    public EditableLevel getLevel() {
        return level;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
