package sabokan.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * Utility class that stores constants and magic numbers /shy
 * @author anaka
 */
public final class Constants {

    /**
     * The scale of the whole game application. Default is 0.5
     */
    private static final float SCALE = 0.5f;
    /**
     * The tile width based on the scale
     */
    public static final int DX = (int) (100 * SCALE); //100 pixels default
    /**
     * the tile height based on the height
     */
    public static final int DY = (int) (85 * SCALE); // 85 pixels default
    /**
     * The height of all textures
     */
    public static final int IMAGE_HEIGHT = (int) (171 * SCALE);
    /**
     * The width of all textures
     */
    public static final int IMAGE_WIDTH = (int) (101 * SCALE);
    /**
     * The height of the rendered status bar
     */
    public static final int STATUSBAR_HEIGHT = 82;
    /**
     * The width of the rendered status bar
     */
    public static final int STATUSBAR_WIDTH = 640;
    /**
     * The height of the rendered side bar
     */
    public static final int SIDEBAR_HEIGHT = 318;
    /**
     * The height of the rendered side bar
     */
    public static final int SIDEBAR_WIDTH = 122;
    /**
     * Magic number, do not ask, accept it and move forward
     */
    public static final int OFFSET = (int) (30 * SCALE);
    /**
     * Path to where all images are located
     */
    public static final String RESOURCES = "/sabokan/game/resources/";
    /**
     * Our cache. Holds all images keyed on their name
     */
    private static Map<String, Image> imageCache = null;
    /**
     * Default dialog Font
     */
    public static final Font DIALOG_FONT;
    /**
     * Default status bar font
     */
    public static final Font STATUSBAR_FONT;

    //this block initializes the fonts
    //if the fonts cannot be located, then some system default fonts are loaded instead
    static {
        Font tmpDialogFont = new Font("Monospace", Font.BOLD | Font.ITALIC, 16);
        Font tmpStatusBarFont = new Font("Monospace", Font.BOLD, 42);
        try {
            tmpDialogFont = Font.createFont(Font.TRUETYPE_FONT, Constants.class.getResourceAsStream(RESOURCES + "fonts/DOTT.ttf")).deriveFont(Font.BOLD | Font.ITALIC, 24f); //new Font("DOTT", Font.BOLD | Font.ITALIC, 16);
            tmpStatusBarFont = Font.createFont(Font.TRUETYPE_FONT, Constants.class.getResourceAsStream(RESOURCES + "fonts/CHLORINP.ttf")).deriveFont(Font.BOLD, 50f); //new Font("CHLORINP", Font.BOLD, 42);
        } catch (Exception ex) {
            Trace.error("Unable to load fonts!...");
            Trace.error(ex);
        } finally {
            DIALOG_FONT = tmpDialogFont; //new Font("DOTT", Font.BOLD | Font.ITALIC, 16);
            STATUSBAR_FONT = tmpStatusBarFont; //new Font("CHLORINP", Font.BOLD, 42);
        }
    }
    /**
     * Default color for text in status bar
     */
    public static final Color STATUSBAR_TEXT_COLOR = new Color(255, 255, 255, 150);
    /**
     * Default color for the games background
     */
    public static final Color BACKGROUND_COLOR = new Color(69, 78, 91, 225);
    /**
     * Default color for the background of the canvas where dialogs are painted
     */
    public static final Color DIALOG_BACKGROUND_COLOR = new Color(180, 125, 0, 90);
    /**
     * Default color for the Text in dialogs
     */
    public static final Color DIALOG_TEXT_COLOR = new Color(0, 0, 0, 70);
    /**
     * Default color for the right bar inside the side bar //hey that rhymes?
     */
    public static final Color MOVES_LEFT_COLOR = new Color(200, 160, 0, 180);
    /**
     * Default color for the progress bar
     */
    public static final Color PROGRESS_COLOR = new Color(255, 0, 0, 180);
    /**
     * Default moves allowed for player to finish the level
     */
    public static int DEFAULT_MOVES = 100;

    /**
     * I am a Utility class, that means there is nothing object oriented here for you
     * So bug off, leave me on my functional small world
     */
    private Constants() {
    }

    public static Image getResourceAsImage(String name) {
        if (imageCache != null) {
            if (name != null && name.length() > 0) {
                return imageCache.get(name);
            }
        }

        return null;
    }

    /**
     * Run whenever you need the textures to start loading
     * @param comp 
     */
    public static void initImageCache(Component comp) {
        if (imageCache == null) {
            try {
                imageCache = new HashMap<String, Image>(IMAGES_TO_LOAD.length + 10);
                MediaTracker tracker = new MediaTracker(comp);
                Trace.info("Initializing textures. Please wait...");
                int id = 0;
                for (String img : IMAGES_TO_LOAD) {
                    Image tmp = grabImage(img);
                    tracker.addImage(tmp, id++);
                    imageCache.put(img, tmp);
                }
                //custom image loading! ugly, but will do the trick
                Image tmp = getLoadDirectImage("SpeechBubble.png").getScaledInstance(DX / 2, -1, Image.SCALE_SMOOTH);
                tracker.addImage(tmp, id++);
                imageCache.put("SpeechBubbleSmall.png", tmp);
                long before = System.currentTimeMillis();
                while (!tracker.checkAll()) { //not finished loading yet
                    tracker.waitForAll(10);
                }
                long loadingTime = (System.currentTimeMillis() - before);
                Trace.info("Initializing textures completed in " + loadingTime + "ms !");
            } catch (Exception e) {
                Trace.error(e);
                Trace.error("Failed to load textures... System exits..." + e.toString());
                System.exit(-1);
            }
        }
    }

    /**
     * Retrieves an image from its physical path
     * @param name
     * @return 
     */
    private static Image grabImage(String name) {
        return new ImageIcon(Constants.class.getResource(RESOURCES + name)).getImage().getScaledInstance(DX, -1, Image.SCALE_SMOOTH);
    }

    /**
     * Retrieves an image from its physical path, ignores cache!
     * Use with caution
     * @param name 
     */
    public static Image getLoadDirectImage(String name) {
        try {
            return new ImageIcon(Constants.class.getResource(RESOURCES + name)).getImage();
        } catch (Exception ex) {
            Trace.error("Failed to load image " + name + " ..." + ex.toString());
        }
        return null;
    }
    
    /**
     * The images that are going to be loaded 
     */
    private static final String[] IMAGES_TO_LOAD = {"Brown Block.png", "Character Boy.png",
        "Character Cat Girl.png", "Character Horn Girl.png", "Character Pink Girl.png",
        "Character Princess Girl.png", "Chest Closed.png", "Chest Lid.png",
        "Chest Open.png", "Dirt Block.png", "Door Tall Closed.png", "Door Tall Open.png",
        "Enemy Bug.png", "Gem Blue.png", "Gem Green.png", "Gem Orange.png", "Grass Block.png",
        "Heart.png", "Key.png", "Plain Block.png", "Ramp East.png", "Ramp North.png", "Ramp South.png",
        "Ramp West.png", "Rock.png", "Roof East.png", "Roof North East.png",
        "Roof North West.png", "Roof North.png", "Roof South East.png",
        "Roof South West.png", "Roof South.png", "Roof West.png",
        "Selector.png", "Shadow East.png", "Shadow North East.png",
        "Shadow North West.png", "Shadow North.png", "Shadow Side West.png",
        "Shadow South East.png", "Shadow South West.png", "Shadow South.png",
        "Shadow West.png", "sideBar.PNG", "SpeechBubble.png", "Star.png",
        "statusBar.PNG", "Stone Block Tall.png", "Stone Block.png",
        "Tree Short.png", "Tree Tall.png", "Tree Ugly.png", "Wall Block Tall.png",
        "Wall Block.png", "Water Block.png", "Window Tall.png", "Wood Block.png"};
}
