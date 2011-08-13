package sabokan.game.entities.tiles;

import java.awt.Image;
import sabokan.game.Constants;

/**
 *
 * @author anaka
 */
public enum Tile {

    WALL('W', "Stone Block Tall.png", false),
    WATER('w', "Water Block.png", false),
    STONE('T', "Stone Block.png", true),
    DIRT('d', "Dirt Block.png", true),
    GROUND('g', "Brown Block.png", true),
    PATH(' ', "Plain Block.png", true),
    TILE('S', "Stone Block.png", true),
    HOLE('O', null, false),
    EXIT('E', "Stone Block.png", true),
    GRASS('G', "Grass Block.png", true),
    OUTOFBOUNDS('X', null, false);
    
    protected final char value;
    protected final Image texture;
    protected final boolean walkable;

    private Tile(char value, String url, boolean walkable) {
        this.walkable = walkable;
        this.value = value;
        this.texture = Constants.getResourceAsImage(url);
    }

    public static Tile getTile(char c) {
        for (Tile tile : Tile.values()) {
            if (tile.value == c) { //you are looking for this I guess
                return tile;
            }
        }
        return OUTOFBOUNDS;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public Image getTexture() {
        return texture;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.name());
    }
}
