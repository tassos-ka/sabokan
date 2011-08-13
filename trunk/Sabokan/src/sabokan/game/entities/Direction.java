package sabokan.game.entities;

import java.awt.Point;

/**
 * Represents Directions inside the game World
 * @author anaka
 */
public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;
    
    /**
     * Returns the resulting position when moving
     * towards the given direction from the given point
     * @param from
     * @param dir
     * @return 
     */
    public static Point movePosition(Point from, Direction dir) {
        if (from != null && dir != null) { // do something
            int x = from.x;
            int y = from.y;
            
            switch(dir) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
                case LEFT:
                    x--;
                    break;
            }
            
            return new Point(x, y);
        } else {
            //?? ti na perimenei piso?
            throw new UnsupportedOperationException("Null arguments are not permitted here");
        }
    }
}
