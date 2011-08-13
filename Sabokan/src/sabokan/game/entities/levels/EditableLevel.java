package sabokan.game.entities.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import sabokan.game.Constants;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.players.impl.PlayerBoy;
import sabokan.game.entities.tiles.Tile;

/**
 *
 * @author anaka
 */
public abstract class EditableLevel extends Level {

    private final int size = 9;
    private Point selected = new Point();
    private static final Color selectedColor = new Color(255, 125, 0, 90);
    private static final Color unselectedColor = new Color(0, 0, 0, 10);
    
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
        player = new PlayerBoy(0, 0);
        return player;
    }

    @Override
    protected Tile[][] initializeLevel() {
        return new Tile[size][size];
    }
    
    public void setTileAt(Point p, Tile tile) {
        setTileAt(p.x, p.y, tile);
    }

    public void setTileAt(int x, int y, Tile tile) {
        if (x >= 0 && x < level.length
                && y >= 0 && y < level[0].length) { //valid tile
            level[x][y] = tile;
        }
    }
    
    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public void addBox(Box box) {
        this.boxes.add(box);
    }
    
    public void addCharacter(Char character) {
        this.characters.add(character);
    }

    public void selectTile(Point p) {
        this.selected = p;
    }

    @Override
    public void paint(Graphics2D g) {
        int x = 0;
        int y = 0;
        for (Tile[] row : level) { //painting
            for (Tile tile : row) {
                if (tile != null) {
                    g.drawImage(tile.getTexture(), x, y, null); 
                }
                renderTileBorder(x, y, false, unselectedColor, g);
                y += Constants.DY;
            }
            y = 0;
            x += Constants.DX;
        }
        
        renderTileBorder(selected.x, selected.y, true, selectedColor, g);
       
    }

    public Point getSelected() {
        return selected;
    }

    private void renderTileBorder(int x, int y, boolean isSelected, Color color, Graphics2D g) {
        g.setPaint(color);
        if (isSelected) {  
            g.fill3DRect(selected.x * Constants.DX, selected.y * Constants.DY, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, true);
        } else {
            g.fill3DRect(x, y, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, true);
        }
    }
}
