package sabokan.game.entities.levels.impl;

import java.util.List;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.boxes.impl.RockBox;
import sabokan.game.entities.characters.impl.PrincessChar;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.items.impl.collectables.BlueGem;
import sabokan.game.entities.items.impl.collectables.Bug;
import sabokan.game.entities.levels.Level;
import sabokan.game.entities.players.impl.PlayerBoy;
import sabokan.game.entities.tiles.Tile;

/**
 *
 * @author anaka
 */
public class FirstLevel extends Level {

    private final static int size = 12;
    private final static String lvl = 
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGG\n" +
            "GGGGGGGGGGGE\n"; //all tiles are blank

    @Override
    protected Tile[][] initializeLevel() {
        return loadLevel(lvl, size);
    }

    @Override
    public List<Box> initBoxes() {
        boxes.clear();
        boxes.add(new RockBox(8, 9));
        boxes.add(new RockBox(8, 8));
        boxes.add(new RockBox(10, 9));
        return boxes;
    }

    @Override
    public Player initPlayer() {
        player = new PlayerBoy(0, 0);
        return player;
    }

    @Override
    public List<Char> initCharacters() {
        characters.clear();
        characters.add(new PrincessChar(9, 9, "Isn't this game amazing?"));
        return characters;
    }
    
    @Override
    public List<Item> initItems() {
        items.clear();
        items.add(new Bug(7, 4));
        items.add(new Bug(6, 4));
        items.add(new Bug(5, 4));
        items.add(new Bug(8, 4));
        items.add(new BlueGem(9, 4));
        return items;
    }
}
