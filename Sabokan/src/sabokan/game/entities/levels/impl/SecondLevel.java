package sabokan.game.entities.levels.impl;

import java.util.List;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.boxes.impl.RockBox;
import sabokan.game.entities.boxes.impl.TreeBox;
import sabokan.game.entities.characters.impl.PrincessChar;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.items.impl.collectables.Key;
import sabokan.game.entities.items.impl.lockables.Chest;
import sabokan.game.entities.levels.Level;
import sabokan.game.entities.players.impl.PlayerBoy;
import sabokan.game.entities.tiles.Tile;

/**
 *
 * @author anaka
 */
public class SecondLevel extends Level {

    private final static int size = 9;
    private final static String lvl =
            "WWWWWWWWW\n"
            + "WSGGGGGGW\n"
            + "WGGGGOGGW\n"
            + "WGGGGGGGW\n"
            + "WGGGGGGGW\n"
            + "WGOGGGOGW\n"
            + "WGGGGGGGW\n"
            + "WGGGGGGGW\n"
            + "WGGGGGGEW"; //all tiles are blank

    @Override
    protected Tile[][] initializeLevel() {
        return loadLevel(lvl, size);
    }

    @Override
    public List<Box> initBoxes() {
        boxes.clear();
        boxes.add(new RockBox(1, 2));
        boxes.add(new RockBox(1, 5));
        boxes.add(new TreeBox(2, 3));
        boxes.add(new TreeBox(3, 3));
        return boxes;
    }

    @Override
    public Player initPlayer() {
        player = new PlayerBoy(1, 1);
        return player;
    }

    @Override
    public List<Char> initCharacters() {
        characters.clear();
        characters.add(new PrincessChar(3, 5, "alitheia tora"));
        return characters;
    }

    @Override
    public List<Item> initItems() {
        items.clear();
        items.add(new Key(5, 6));
        items.add(new Chest(7, 2));
        return items;
    }
}
