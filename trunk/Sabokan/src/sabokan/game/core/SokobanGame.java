package sabokan.game.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sabokan.game.Constants;
import sabokan.game.GamePanel;
import sabokan.game.Trace;
import sabokan.game.entities.boxes.Box;
import sabokan.game.entities.characters.Char;
import sabokan.game.entities.items.Item;
import sabokan.game.entities.levels.Level;
import sabokan.game.entities.players.Player;
import sabokan.game.entities.levels.impl.*;
import sabokan.game.events.*;
import sabokan.game.utils.DialogCarrier;
import sabokan.game.utils.Lock;

/**
 *
 * @author anaka
 */
public class SokobanGame {

    private Player player;
    private List<Box> boxes;
    private List<Char> characters;
    private List<Item> items;
    private Level level;
    private final KeyListener gameKeyListener;
    private final GamePanel container;
    private final List<Class<? extends Level>> allLevels;
    private int currentLevelIdx = 0;
    private final Lock lock = new Lock();
    private volatile DialogCarrier currentDialog;
    private final StatusBar statusBar = new StatusBar();

    public SokobanGame(GamePanel container) {
        if (container != null) {
            Trace.info("Started");
            this.container = container;
            Constants.initImageCache(container);
            Trace.info("Loading all levels");
            allLevels = initLevels();
            initFirstLevel();
            gameKeyListener = initGameListener();
            Trace.info("Listener intialized");
        } else {
            throw new UnsupportedOperationException("Unable to initialize without defining a container frame!");
        }
        Trace.info("Game initialized");
    }

    private void initFirstLevel() {
        currentLevelIdx = 0;
        initNextLevel(null);
    }

    private void initNextLevel(File file) {
        initLevel(currentLevelIdx++, file);
    }

    private void initLevel(int idx, File file) {
        level = loadLevel(idx, file);
        Trace.info("Level initialized");
        player = level.initPlayer();
        player.setInventory(statusBar.getInventory()); //refresh inventory
        Trace.info("Player initialized");
        boxes = level.initBoxes();
        Trace.info("Boxes initialized");
        characters = level.initCharacters();
        Trace.info("Characters initialized");
        items = level.initItems();
        Trace.info("Items initialized");
        //ready for the resize effect!
        container.fitToLevelSize(getSize());
        repaint();
    }

    public void updateGame() throws FinishedLevelEvent, DialogEvent, ItemEvent, PlayerDiedEvent, PlayerWonEvent {
        player.update(level);
        for (Box box : boxes) {
            box.update();
        }
        for (Char aChar : characters) {
            aChar.update(player);
        }

        for (Item item : items) {
            item.update(player);
        }
    }

    public void paintGame(Graphics2D g) {
        
        //fill background
        g.setPaint(Constants.BACKGROUND_COLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);

        //paint status and sidebar
        statusBar.paint(g);
        g.translate(Constants.SIDEBAR_WIDTH, Constants.STATUSBAR_HEIGHT);
        
        //start painting game
        level.paint(g);
        for (Item item : items) {
            item.paint(g);
        }
        for (Box box : boxes) {
            box.paint(g);
        }
        for (Char aChar : characters) {
            aChar.paint(g);
        }
        if (currentDialog != null) { //render also dialog
            currentDialog.paint(g);
        }
        player.paint(g);
        
        //translate back
        g.translate(-Constants.SIDEBAR_WIDTH, Constants.STATUSBAR_HEIGHT);
        
        paintMovesLeft(g);
        paintProgress(g);
    }

    private Level loadLevel(int l, File file) {
        if (file == null) { //no file specified, load the crappy defualts
            try {
                return allLevels.get(l).newInstance();
            } catch (Exception ex) {
                Trace.error("Unable to instantiate next level!");
                JOptionPane.showMessageDialog(container, "That were all the levels defined! Starting from level 1!");
                currentLevelIdx = 0;
                return loadLevel(currentLevelIdx++, null);
            }
        } else {//file loading case
            return new FromFileLevel(file);
        }
    }

    private KeyListener initGameListener() {
        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (checkPauseStatus(e)) {
                    return;
                }
                player.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (checkPauseStatus(e)) {
                    return;
                }
                player.keyPressed(e);
                updateAndRepaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (checkPauseStatus(e)) {
                    return;
                }
                player.keyReleased(e);
            }
        };
    }

    protected void repaint() {
        container.repaint();
    }

    protected void updateAndRepaint() {
        try {
            updateGame();
        } catch (ItemEvent itemEvent) { //somehting is done with an item
            processItemEvent(itemEvent);
        } catch (DialogEvent ex) {
            //blah blah time!
            renderDialog(ex.getDialog());
        } catch (FinishedLevelEvent e) {
            repaint();
            JOptionPane.showMessageDialog(container, "Level complete! Proceed to level " + (currentLevelIdx + 1));
            initNextLevel(null);
        } catch (PlayerDiedEvent e) {
            repaint();
            JOptionPane.showMessageDialog(container, "You have died... Such a waste... Bye");
            System.exit(0);
        } catch (PlayerWonEvent e) {
            repaint();
            JOptionPane.showMessageDialog(container, e.getWinningMessage());
            System.exit(0);
        }
        repaint();
    }

    public KeyListener getKeyListener() {
        Trace.info("Someone is retrieving the KeyListener");
        return gameKeyListener;
    }

    private List<Class<? extends Level>> initLevels() {
        List<Class<? extends Level>> res = new ArrayList<Class<? extends Level>>();
        res.add(FirstLevel.class);
        res.add(SecondLevel.class);
        res.add(AutoGeneratedLevel.class);

        return res;
    }

    private boolean checkPauseStatus(KeyEvent... invalidateUs) {
        if (lock.isLocked()) {
            //someone has paused us
            for (KeyEvent event : invalidateUs) {
                event.consume();
            }
            return true;
        }
        return false;
    }

    private void renderDialog(DialogCarrier dialog) {
        try {
            if (currentDialog == null && !lock.isLocked()) {
                dialog.prepare(level.getDimension());
                dialog.nextFrame();
                currentDialog = dialog;
                Thread painter = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        final int waitTime = currentDialog.getTimeBetweenFrames();
                        synchronized (lock) {
                            lock.lock();
                            try {
                                while (currentDialog.nextFrame()) {
                                    repaint();
                                    lock.wait(waitTime);
                                }
                            } catch (Exception e) {
                                //failed to render dialog
                                Trace.error("Failed to paint dialog! " + e.toString());
                            } finally {
                                currentDialog = null;
                                lock.unlock();
                            }
                        }
                    }
                });

                painter.start();
            }
        } catch (Exception e) {
            Trace.error("Failed to render dialog! " + e.toString());
        }
    }

    private void processItemEvent(ItemEvent itemEvent) {
        if (itemEvent != null) {
            if (itemEvent instanceof CollectItemEvent) {
                CollectItemEvent collectItemEvent = (CollectItemEvent) itemEvent;
                player.log("collects " + collectItemEvent.getItem());
                statusBar.addCollectedItem(collectItemEvent.getItem());
                items.remove(collectItemEvent.getItem());
            }
        }
    }

    public void loadLevel(File file) {
        initNextLevel(file);
    }

    private void paintMovesLeft(Graphics2D g) {
        g.setColor(Constants.MOVES_LEFT_COLOR);
        int movesBarTop = 87;
        int movesBarMaxLength = 111;
        int length = (int)(movesBarMaxLength * player.getMovesLeft());
        int offset = movesBarMaxLength - length;
        g.fillRoundRect(45, movesBarTop + offset, 26, length, 10, 10);
    }

    private void paintProgress(Graphics2D g) {
        g.setColor(Constants.PROGRESS_COLOR);
        int movesBarTop = 87;
        int movesBarMaxLength = 111;
        int length = (int)(movesBarMaxLength * statusBar.getInventory().getPercentageOfBugsCollected());
        int offset = movesBarMaxLength - length;
        g.fillRoundRect(7, movesBarTop + offset, 26, length, 10, 10);
    }
    
    public Dimension getSize() {
        Dimension levelSize = level.getDimension();
        int width = Math.max(Constants.STATUSBAR_WIDTH, Constants.SIDEBAR_WIDTH + levelSize.width + Constants.IMAGE_WIDTH);
        int height = Math.max(Constants.SIDEBAR_HEIGHT,Constants.STATUSBAR_HEIGHT + levelSize.height + Constants.IMAGE_HEIGHT);
        return new Dimension(width, height);
    }
}
