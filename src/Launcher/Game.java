package Launcher;
import Entity.Enemy;
import Entity.Player;
import Input.KeyManager;
import Input.MouseManager;
import StatePackage.*;
import Window.Display;
import Graphics.*;
import sun.net.ProgressSource;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * Created by William on 2016-04-27.
 */
public class Game implements Runnable {
    private Display display;
    private KeyManager keyManager;

    private int width, height;
    private String title;

    private Thread thread;
    private Boolean running = false;

    private GameState gameState;
    private MenuState menuState;
    private ShopState shopState;
    private MouseManager mouseManager;

    private Player player;
    private Menu menu;
    private InGame inGame;
    private Shop shop;

    // Variables for graphics/canvas
    private BufferStrategy bs;
    private Graphics g;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        menu = new Menu(this);
        inGame = new InGame(this);
        shop = new Shop(this);

    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addMouseListener(menu);
        display.getFrame().addMouseMotionListener(menu);
        display.getCanvas().addMouseListener(menu);
        display.getCanvas().addMouseMotionListener(menu);
        display.getFrame().addMouseListener(inGame);
        display.getFrame().addMouseMotionListener(inGame);
        display.getCanvas().addMouseListener(inGame);
        display.getCanvas().addMouseMotionListener(inGame);
        display.getFrame().addMouseListener(shop);
        display.getFrame().addMouseMotionListener(shop);
        display.getCanvas().addMouseListener(shop);
        display.getCanvas().addMouseMotionListener(shop);

        // initialize all the images from the SpriteSheet
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this, menu);
        shopState = new ShopState(this);


        player = new Player(this, gameState, menuState, shopState, mouseManager);
        gameState.setPlayer(player);
        menu.setMenuState(menuState);
        gameState.setInGame(inGame);
        inGame.setGameState(gameState);
        inGame.setPlayer(player);
        shop.setShopState(shopState);
        shopState.setShop(shop);
        StateManager.setCurrentState(menuState);

    }

    private void tick(){
        keyManager.tick();

        if(StateManager.getCurrentState() != null)
            StateManager.getCurrentState().tick();

    }

    private void render() {
        // a way for computer to draw to the screen
        // it is a hidden screen producing new screen before it is rendered
        bs = display.getCanvas().getBufferStrategy();

        //single instance of buffer
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        // so it gets the paintbrush allowing it to draw on the frame
        // allowing ability to draw after g is initialized
        g = bs.getDrawGraphics();
        //clear screen before it starts drawing
        g.clearRect(0,0,width, height);

        /////////////////////////////////
        if(StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }


        ////////////////////////////////////
        // Last parameter is the Image observer

        // so the bufferstrategy brings the image on the frame
        bs.show();
        //after it dispose to the next buffer strategy can be rendered when render is called again in the game loop
        g.dispose();


    }

    // produce the game loop and run the game
    @Override
    public void run() {

        // use helper to init the Display and create a new Frame and Canvas rather than in the constructor
        init();


        // Setting up for ticking
        int fps = 60;
        // In nanoSeconds so 1 000 000 000 is 1 second
        // Thus time per tick is the amount of time require for 1 tick since we want 60 tick per second
        double timePerTick = 1000000000 / fps;
        // Previous time
        double delta = 0;
        long now;
        // Current Time in nanoSecnod
        long lastTime = System.nanoTime();

        //begin the game loop to tick the things and render it constantly if running is true, set from start
        while(running) {
            now = System.nanoTime();
            // if the amount of time passed from last time till now is larger than timePerTick then its >= 1 thus tick/render
            // if the amount of time passed is lower than timePerTick then its < 1 thus no tick/render
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
            }

        }

        // when running is false so we stop it;
        stop();

    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    // only when there is threading
    public synchronized void start() {
        if(running) {
            return;
        }
        running = true;
        //initialize thread to this Game(singleton)
        thread = new Thread(this);

        // it will call the RUN method when u begin thread.start
        thread.start();

    }

    // only when there is threading
    public synchronized void stop() {
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public ShopState getShopState() {
        return shopState;
    }


}
