package sabokan.game.entities.levels;

import sabokan.game.entities.players.Player;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.boxes.Box;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import sabokan.game.Constants;
import sabokan.game.Trace;
import sabokan.game.entities.Renderable;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.tiles.Tile;

/**
 *
 * @author anaka
 */
public abstract class Level implements Renderable {

    protected Tile[][] level;
    protected final List<Box> boxes = new ArrayList<Box>();
    protected final List<Char> characters = new ArrayList<Char>();
    protected final List<Item> items = new ArrayList<Item>();
    protected Player player = null;

    public Level() {
        this.level = initializeLevel();
    }

    protected abstract Tile[][] initializeLevel();

    public Tile getTileAt(Point p) {
        return getTileAt(p.x, p.y);
    }

    public Tile getTileAt(int x, int y) {
        if (x >= 0 && x < level.length
                && y >= 0 && y < level[0].length) { //valid tile
            return level[x][y];
        } else {
            return Tile.OUTOFBOUNDS;
        }
    }

    public boolean isWalkable(Point p) {
        return getTileAt(p).isWalkable() && isCharacterThere(p) == null && isBlockingItemThere(p) == null;
    }

    public boolean isFree(Point p) {
        return isWalkable(p) && isBoxThere(p) == null;
    }

    public Box isBoxThere(Point p) {
        if (p != null) {
            for (Box box : boxes) {
                if (p.equals(box.getPosition())) {
                    return box; /// collides with box
                }
            }
        }
        return null;
    }

    public Char isCharacterThere(Point p) {
        if (p != null) {
            for (Char character : characters) {
                if (p.equals(character.getPosition())) {
                    return character; /// collides with character
                }
            }
        }
        return null;
    }

    private Item isBlockingItemThere(Point p) {
        if (p != null) {
            for (Item item : items) {
                if (!item.canWalkThrough() && p.equals(item.getPosition())) {
                    return item; /// collides with item
                }
            }
        }
        return null;
    }

    public boolean hasWon(Point p) {
        return getTileAt(p) == Tile.EXIT;
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
                y += Constants.DY;
            }
            y = 0;
            x += Constants.DX;
        }
    }

    @Override
    public Image getTexture() {
        throw new UnsupportedOperationException("levels do not have an image associated yet");
    }

    public Tile[][] loadLevel(final String lvl, final int size) {
        BufferedReader reader = null;
        Tile[][] lev = null;
        try {
            lev = new Tile[size][size];
            reader = new BufferedReader(new StringReader(lvl));
            char current = (char) reader.read();
            int row = 0;
            int col = 0;
            while (current > 0) { //still data inside
                if (current == '\n') { //endline detected
                    row = 0;
                    col++;
                } else { //process tile
                    lev[row++][col] = Tile.getTile(current);
                }
                current = (char) reader.read();
            }
        } catch (Exception e) {
            //failed royally
        } finally {
            try {
                reader.close();
            } catch (Exception ahem) {
            }

        }
        return lev;
    }

    public Tile[][] loadFromFile(File file) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        String lvl = null;
        int size = -1;
        try {

            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            Object obj = in.readObject();
            while (obj != null) {
                if (obj instanceof Player) {
                    Trace.info("Reading Player");
                    player = (Player)obj;
                } else if (obj instanceof Box) {
                    Trace.info("Reading Box");
                    boxes.add((Box)obj);
                } else if (obj instanceof Item) {
                    Trace.info("Reading Item");
                    items.add((Item)obj);
                } else if (obj instanceof Char) {
                    Trace.info("Reading Character");
                    characters.add((Char)obj);
                } else if (obj instanceof String){
                    Trace.info("Reading Tiles");
                    lvl = (String) obj;
                } else if (obj instanceof Integer) {
                    Trace.info("Reading Size");
                    size = Integer.valueOf((Integer)obj);
                }
                obj = in.readObject();
            }
            throw new IllegalStateException("If you reached here, well something is wrong with the file");
            
        } catch (java.io.EOFException ex) { //finished parsing?, ugly, but no method to find this out
            return loadLevel(lvl, size);
        }catch (Exception ex) {
            Trace.error("Failed to read from file! : " + ex.toString());
            throw new IllegalArgumentException("Failed to read from file!", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignore) {
                    //gimme a break already
                }
            }
        }
    }

    public void writeToFile(File file) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            //write player
            out.writeObject(player);
            
            //write boxes
            for (Box box : boxes) {
                out.writeObject(box);
            }
            //write items
            for (Item item : items) {
                out.writeObject(item);
            }

            //write chars
            for (Char character : characters) {
                out.writeObject(character);
            }

            //write tiles
            StringBuilder buf = new StringBuilder(level.length * level[0].length);
            for (int i = 0; i < level[0].length; i++) {
                for (int j = 0; j < level.length; j++) {
                    buf.append(level[j][i] != null ? level[j][i].getValue() : Tile.HOLE.getValue());
                }
                buf.append('\n');
            }
            out.writeObject(buf.toString());
            out.writeObject(level.length);
            
        } catch (Exception ex) {
            Trace.error("Failed to write to file! : " + ex.toString());
            throw new IllegalArgumentException("Failed to write to file!", ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ignore) {
                    //gimme a break already
                }
            }
        }
    }

    public abstract List<Box> initBoxes();

    public abstract List<Char> initCharacters();

    public abstract List<Item> initItems();

    public abstract Player initPlayer();

    public Dimension getDimension() {
        Dimension dim = new Dimension();
        dim.width = level.length * Constants.DX;
        dim.height = level[0].length * Constants.DY;
        return dim;
    }
}
