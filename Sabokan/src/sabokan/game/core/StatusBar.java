package sabokan.game.core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import sabokan.game.Constants;
import sabokan.game.Trace;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.items.Usable;
import sabokan.game.utils.Lock;

/**
 *
 * @author anaka
 */
public class StatusBar {

    private final Inventory inventory = new Inventory();
    private final Image statusBarTexture;
    private final Image sideBarTexture;

    public StatusBar() {
        try {
            Lock lock = new Lock();
            synchronized (lock) {
                statusBarTexture = Constants.getLoadDirectImage("statusBar.PNG");//.getScaledInstance(-1, Constants.STATUSBAR_HEIGHT, Image.SCALE_SMOOTH);
                sideBarTexture = Constants.getLoadDirectImage("sideBar.PNG");

                while (statusBarTexture.getWidth(null) == -1) {
                    lock.wait(100);
                }
            }
        } catch (Exception ex) {
            Trace.error(ex);
            throw new IllegalArgumentException("Textures not found");
        }
    }

    public void addCollectedItem(Item item) {
        inventory.add(item);
    }

    public void removeCollectedItem(Item item) {
        inventory.remove(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void initialize() {
        inventory.clear();
    }

    public void paint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(statusBarTexture, 0, 0, statusBarTexture.getWidth(null), statusBarTexture.getHeight(null), null);
        g.drawImage(sideBarTexture, 0, Constants.STATUSBAR_HEIGHT, sideBarTexture.getWidth(null), sideBarTexture.getHeight(null), null);
        paintSideBarItems(g);
        paintStatusBarItems(g);
    }

    private void paintSideBarItems(Graphics2D g) {
        int x = 15;
        int y;

        g.setColor(Constants.STATUSBAR_TEXT_COLOR);
        for (Item item : inventory.getUsableItems()) {
            if (((Usable) item).isFirstSlot()) {
                y = 70;
            } else {
                y = 150;
            }
            g.drawImage(item.getTexture(), x, y, null);
            g.drawString("x" + inventory.quantityOf(item), x - 15 + Constants.IMAGE_WIDTH, y + 75);
        }
    }

    private void paintStatusBarItems(Graphics2D g) {
        g.setFont(Constants.STATUSBAR_FONT);
        g.setColor(Constants.STATUSBAR_TEXT_COLOR);
        final int height = g.getFontMetrics().getAscent() + 20;
        int x = 20;
        int y = -Constants.OFFSET + 5;

        for (Item item : inventory.getNotUsableItems()) {
            g.drawImage(item.getTexture(), x, y, null);
            x += Constants.IMAGE_WIDTH + 10;
            g.drawString("x" + inventory.quantityOf(item), x, height);
            x += 120;
        }
    }
}