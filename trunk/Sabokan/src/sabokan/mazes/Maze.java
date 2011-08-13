package sabokan.mazes;

import java.util.Random;

/**
 * The Maze class
 * @author anaka
 */
public class Maze {

    private static final char clear = ' ';
    private static final char wall = '\u2588';
    private static final char start = 'S';
    private static final char end = 'E';
    private static final char visit = 'V';
    private final int size;
    private static final int defaultSize = 10;
    private final char[][] maze;
    private final boolean[][] visited;
    private int startX = -1;
    private int startY = -1;

    /**
     * Default constructor uses default size
     */
    public Maze() {
        this(defaultSize);
    }

    /**
     * True if the x,y is a Wall
     * @param x
     * @param y
     * @return 
     */
    public boolean isWall(int x, int y) {
        if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
               return maze[x][y] == wall;
        }
        return true;
    }
    
    /**
     * True if the x,y is Start
     * @param x
     * @param y
     * @return 
     */
    public boolean isStart(int x, int y) {
        if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
               return maze[x][y] == start;
        }
        return false;
    }

    /**
     * True if the x,y has been marked as visited
     * @param x
     * @param y
     * @return 
     */
    public boolean isVisited(int x, int y) {
        if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
               return maze[x][y] == visit;
        }
        return false;
    }

    /**
     * True if the x,y is the End
     * @param x
     * @param y
     * @return 
     */
    public boolean isEnd(int x, int y) {
        if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
               return maze[x][y] == end;
        }
        return false;
    }

    /**
     * Marks x,y as visited
     * @param x
     * @param y 
     */
    public void visitPath(int x, int y) {
        if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
               maze[x][y] = visit;
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append(wall);
        for (int x = 0; x < size; x++) {
            buf.append(wall);
        }
        buf.append(wall).append("\n");

        for (int x = 0; x < size; x++) {
            buf.append(wall);
            for (int y = 0; y < size; y++) {
                buf.append(maze[x][y]);
            }
            buf.append(wall).append("\n");
        }

        buf.append(wall);
        for (int x = 0; x < size; x++) {
            buf.append(wall);
        }
        buf.append(wall).append("\n");

        return buf.toString();
    }

    /**
     * Dynamic size constructor
     * @param size 
     */
    public Maze(final int size) {
        this.size = size;
        maze = new char[this.size][this.size];
        visited = new boolean[this.size][this.size];
        resetMaze();
        markStart();
        markEnd();
    }

    /**
     * Purges the maze, by reseting all x,y points to walls
     */
    private void resetMaze() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                maze[x][y] = wall;
            }
        }
    }

    /**
     * Selects a random point and marks it
     * as the Start
     */
    private void markStart() { //updates also position x and y
        final Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);

        x = x % 2 == 1 ? x + 1 : x;
        x = x < size ? x : 0;

        y = y % 2 == 1 ? y + 1 : y;
        y = y < size ? y : 0;

        maze[x][y] = start;
        this.startX = x;
        this.startY = y;
    }

    /**
     * Selects a random point and marks it
     * as the End. Makes also sure that it does not
     * coexists with the start of the Maze
     */
    private void markEnd() { //always different than start
        final Random rand = new Random();
        int endX = rand.nextInt(size);
        int endY = rand.nextInt(size);
        int off = 0;
        do {
            endX = endX % 2 == 1 ? endX + 1 : endX;
            endX += off;
            endX = endX < size ? endX : 0;

            endY = endY % 2 == 1 ? endY + 1 : endY;
            endY = endY < size ? endY : 0;
            off++;
        } while (endX == this.startX && endY == this.startY);

        maze[endX][endY] = end;
    }

    /**
     * Moves from x,y to the given Direction
     * @param x
     * @param y
     * @param dir
     * @return 
     */
    public boolean move(int x, int y, int dir) {
        boolean placeExists = false;
        switch (dir) {
            case 1:
                placeExists = moveUp(x, y);
                break;
            case 2:
                placeExists = moveDown(x, y);
                break;
            case 3:
                placeExists = moveLeft(x, y);
                break;
            case 4:
                placeExists = moveRight(x, y);
                break;
            default:
        }
        return placeExists;
    }

    /**
     * Calculates the new x coordinate given the old x and the Direction that we are moving
     * @param x
     * @param dir
     * @return 
     */
    public int calcX(int x, int dir) {
        switch (dir) {
            case 3:
                x = x - 2;
                break;
            case 4:
                x = x + 2;
                break;
            default:
        }
        return x;
    }
    
    /**
     * Calculates the new x coordinate given the old y and the Direction that we are moving
     * @param x
     * @param dir
     * @return 
     */
    public int calcY(int y, int dir) {
        switch (dir) {
            case 1:
                y = y - 2;
                break;
            case 2:
                y = y + 2;
                break;
            default:
        }
        return y;
    }

    /**
     * Moves Up one Point
     * @param positionX
     * @param positionY
     * @return 
     */
    public boolean moveUp(int positionX, int positionY) {
        boolean placeExists = false;

        if (positionY - 2 >= 0 && !visited[positionX][positionY - 2]) { //valid
            positionY = positionY - 2;
            visited[positionX][positionY] = true;
            maze[positionX][positionY] = maze[positionX][positionY] != start && maze[positionX][positionY] != end ? clear : maze[positionX][positionY];
            placeExists = true;
        }

        return placeExists;
    }

    /**
     * Moves Down one Point
     * @param positionX
     * @param positionY
     * @return 
     */
    public boolean moveDown(int positionX, int positionY) {
        boolean placeExists = false;

        if (positionY + 2 < size && !visited[positionX][positionY + 2]) { //valid
            positionY = positionY + 2;
            visited[positionX][positionY] = true;
            maze[positionX][positionY] = maze[positionX][positionY] != start && maze[positionX][positionY] != end ? clear : maze[positionX][positionY];
            placeExists = true;
        }

        return placeExists;
    }
    
    /**
     * Moves Left one Point
     * @param positionX
     * @param positionY
     * @return 
     */
    public boolean moveLeft(int positionX, int positionY) {
        boolean placeExists = false;

        if (positionX - 2 >= 0 && !visited[positionX - 2][positionY]) { //valid
            positionX = positionX - 2;
            visited[positionX][positionY] = true;
            maze[positionX][positionY] = maze[positionX][positionY] != start && maze[positionX][positionY] != end ? clear : maze[positionX][positionY];
            placeExists = true;
        }


        return placeExists;
    }

    /**
     * Moves Right one Point
     * @param positionX
     * @param positionY
     * @return 
     */
    public boolean moveRight(int positionX, int positionY) {
        boolean placeExists = false;

        if (positionX + 2 < size && !visited[positionX + 2][positionY]) { //valid
            positionX = positionX + 2;
            visited[positionX][positionY] = true;
            maze[positionX][positionY] = maze[positionX][positionY] != start && maze[positionX][positionY] != end ? clear : maze[positionX][positionY];
            placeExists = true;
        }

        return placeExists;
    }

    /**
     * Retrieves Start X coordinate
     * @return 
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Retrieves Start Y coordinate
     * @return 
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Someone ordered a demolisher?
     * Clears the wall given a destination point and the direction
     * @param destX
     * @param destY
     * @param dir 
     */
    public void clearWall(int destX, int destY, int dir) {
        switch (dir) {
            case 1:
                destY++;
                break;
            case 2:
                destY--;
                break;
            case 3:
                destX++;
                break;
            case 4:
                destX--;
                break;
            default:
        }

        if (destX < size && destY < size && destX >= 0 && destY >= 0) { //valid
            maze[destX][destY] = clear;
        }
    }
}
