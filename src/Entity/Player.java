package Entity;

import Input.MouseManager;
import Launcher.Game;
import StatePackage.GameState;
import StatePackage.MenuState;
import StatePackage.ShopState;
import StatePackage.StateManager;
import Worlds.Tile;
import Worlds.World;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William1115 on 2016-06-02.
 */
public class Player implements Entity {

    private Game game;
    private GameState gameState;
    private MenuState menuState;
    private ShopState shopState;
    private MouseManager mouseManager;
    private World world;

    private  boolean hastower = false;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private Base base;
    private Tower tower = null;
    private int fullHealth;

    private long now, lastTime;

    private int playerHealth, playerGold;

    public Player(Game game, GameState gameState, MenuState menuState, ShopState shopState, MouseManager mouseManager) {
        this.game = game;
        this.mouseManager = mouseManager;
        this.gameState = gameState;
        this.menuState = menuState;
        this.shopState = shopState;
        playerGold = DEFAULT_GOLD;
        enemies = new ArrayList<Enemy>();
        lastTime = System.nanoTime();
        towers =  new ArrayList<Tower>();
    }

    public void setWorld(World world) {
        this.world = world;
        base = new Base(world.getSpawnX() * Tile.getTILEWIDTH(), world.getSpawnY() * Tile.getTILEHEIGHT(), DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
        playerHealth = base.getHealth();
        fullHealth = playerHealth;
    }

    public boolean leftPressedAndWall() {
        if (mouseManager.isLeftPressed() && (mouseManager.getMouseX()/Tile.getTILEWIDTH()) < world.getWidth() && (mouseManager.getMouseY()/Tile.getTILEHEIGHT()) < world.getHeight() )
        return ( world.getTile(mouseManager.getMouseX() / Tile.getTILEWIDTH() , (mouseManager.getMouseY() / Tile.getTILEHEIGHT())).getId() == 1);
        else return false;
    }

    public void setCurrentTowerState(Tower tower) {
        this.tower = tower;
    }


    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }


    @Override
    public void tick() {
       if(leftPressedAndWall() && StateManager.getCurrentState().equals(gameState)) {
           if (this.tower != null && playerGold - this.tower.getCost() >= 0) {


               for (Tower tower : towers) {
                   if (tower.getX() == (mouseManager.getMouseX() / Tile.getTILEWIDTH()) && tower.getY() == (mouseManager.getMouseY() / Tile.getTILEHEIGHT())) {
                       hastower = true;

                       break;
                   }
               }

               if (!hastower) {
                   if (this.tower.getClass() == Tower.class) {
                       int tempX = mouseManager.getMouseX() / DEFAULT_TILE_WIDTH;
                       int tempY = mouseManager.getMouseY() / DEFAULT_TILE_HEIGHT;
                       Tower tower = new Tower(world, tempX * DEFAULT_TILE_WIDTH, tempY * DEFAULT_TILE_HEIGHT);
                       towers.add(tower);
                       playerGold -= tower.getCost();
                       this.tower = null;
                   }
               }
               hastower = false;
           }
       }



        now = System.nanoTime();

        if((now - lastTime) / 1000000000 >= 1 && enemies.size() < 10){
            Enemy enemy = new Enemy(base, world, 0, 0);
            enemies.add(enemy);
            lastTime = now;
        }


        if(StateManager.getCurrentState().equals(gameState)) {
            for (Enemy next : enemies) {
                next.tick();

            }
            for (int j = 0; j < enemies.size(); j++)
                if (!enemies.isEmpty() && enemies.get(j).isDead()){
                    removeEnemy(enemies.get(j));
                }

            for(Tower tower: towers) {
                tower.getFirstEnemyX(enemies);
                tower.getFirstEnemyY(enemies);
                tower.tick();
           }
        }

        playerHealth = base.getHealth();

    }

    @Override
    public void render(Graphics g) {
        if(StateManager.getCurrentState().equals(gameState)) {
            for(Tower tower : towers) {
                tower.render(g);
            }

            for (Enemy next : enemies) {
                next.render(g);
            }



            base.render(g);

            Font f1 = new Font("arial", 1, 20);
            if(playerHealth > fullHealth /2)
                g.setColor(Color.green);
            else g.setColor(Color.yellow);
            if(playerHealth < fullHealth / 10)
                g.setColor(Color.red);
            g.setFont(f1);
            g.drawString(Integer.toString(playerHealth), game.getWidth() - 60, 570);

            g.setColor(Color.ORANGE);
            Font f2 = new Font("arial", 1, 25);
            g.setFont(f2);
            g.drawString("Health:", game.getWidth() - 85, 550);

            g.setColor(Color.ORANGE);
            g.setFont(f2);
            g.drawString("Gold:", game.getWidth() - 75, 610);

            g.setColor(Color.green);
            g.setFont(f1);
            g.drawString(Integer.toString(playerGold), game.getWidth() - 60, 630);

        }
    }
}
