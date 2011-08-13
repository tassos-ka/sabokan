package sabokan.game.entities.levels.impl;

import java.io.File;
import java.util.List;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.levels.Level;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.players.impl.PlayerBoy;
import sabokan.game.entities.tiles.Tile;

/**
 *
 * @author anaka
 */
public class FromFileLevel extends Level {

    private final File loadFrom;

    public FromFileLevel() {
        this(null);
    }
    
    public FromFileLevel(File loadFrom) {
        if (loadFrom != null && loadFrom.canRead()) {
            this.loadFrom = loadFrom;
            level = initializeLevel();
        } else {
            throw new IllegalArgumentException("The file you provided is corrupted");
        }

    }

    @Override
    protected Tile[][] initializeLevel() {
        if (loadFrom != null) {
            return loadFromFile(loadFrom);
        } else {
            return null;
        }
    }

    @Override
    public List<Box> initBoxes() {
        return boxes;
    }

    @Override
    public List<Char> initCharacters() {
        return characters;
    }

    @Override
    public List<Item> initItems() {
        return items;
    }

    @Override
    public Player initPlayer() {
        return player;
    }
    
}
