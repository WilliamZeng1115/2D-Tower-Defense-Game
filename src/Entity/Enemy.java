package Entity;

import Worlds.Tile;
import Worlds.World;
import Graphics.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by William1115 on 2016-06-02.
 */
public class Enemy extends NonPlayer {
    private Base base;
    private World world;
    private String direction = "";
    private long now, lastTime;
    private int damage;
    private double enemyRatio;
    private LinkedList<Bullet> bullets;
    private int tempHealth;
    private static int healthIncrease;


    public Enemy(Base base, World world, float x, float y) {
        super(x, y, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
        this.base = base;
        this.world = world;
        damage = DEFUALT_DAMAGE;
        bullets = new LinkedList<Bullet>();
        health = 10 + healthIncrease;
        increaseCounter();
        tempHealth = health;
        enemyRatio = health / (double) tempHealth;
    }


    public void takeDamage(int x, int y) {
        Tile currentTile = world.getTile((x / DEFAULT_TILE_WIDTH), (y / DEFAULT_TILE_HEIGHT));

        if(currentTile.isDamage() && health > 0)
            health -= currentTile.getDamageNow();

    }

    public boolean takeDamage(int damage) {

        if( health > 0)
            health -= damage;

        return isDead();
    }

    public void addBullet(Bullet b) {
        if (bullets.size()<10)
        bullets.add(b);
    }


    public boolean isDead() {
        return health <= 0;
    }

    private void findMin() {
        int minCost = 9999;
        int x = (int) this.x / DEFAULT_TILE_WIDTH;
        int y = (int) this.y / DEFAULT_TILE_HEIGHT;
        int xRightCorner = ((int) this.x + width - 1) / DEFAULT_TILE_WIDTH;
        int yLeftBottom = ((int) this.y + height - 1) / DEFAULT_TILE_HEIGHT;
        Tile tileUp = world.getTile(x, y - 1);
        Tile tileDown = world.getTile(x , y + 1 );
        Tile tileRight = world.getTile(x + 1, y );
        Tile tileLeft =  world.getTile(x - 1, y);

        if (world.getTile(x,y).getCost() == 0) {
            base.decrementHealth(damage);
            health = 0;
            //implement enemy removal
            return;
        }

        minCost = Math.min(minCost, tileUp.getCost());
        minCost = Math.min(minCost, tileDown.getCost());
        minCost = Math.min(minCost, tileRight.getCost());
        minCost = Math.min(minCost, tileLeft.getCost());


        if( y > 0 && minCost == tileUp.getCost()) {
            if(direction.equals("L") && xRightCorner != x) {
                return;
            }
            minCost = world.getTile(x , y - 1).getCost();
            direction = "U";

        }
        if( y < world.getHeight() - 1 && minCost == tileDown.getCost()) {
            if(direction.equals("L") && xRightCorner != x) {
                return;
            }
            minCost = world.getTile(x,y+1).getCost();
            direction = "D";

        }
        if( x > 0 && minCost == tileLeft.getCost()) {
            if(direction.equals("U") && yLeftBottom != y) {
                return;
            }
            minCost = world.getTile(x - 1, y).getCost();
            direction = "L";

        }

        if( x < world.getWidth() - 1 && minCost == tileRight.getCost()) {
            if(direction.equals("U") && yLeftBottom != y) {
                return;
            }
            direction= "R";

        }
    }

    private void advance(){
        findMin();
        if(direction.equals("U"))
            y -= 1;
        else if(direction.equals("D"))
            y += 1;
        else if(direction.equals("L"))
            x -= 1;
        else if(direction.equals("R"))
            x += 1;

    }

    private void bulletHelper() {
        if (!bullets.isEmpty()) {
            for (Bullet bullet : bullets) {

                bullet.trackEnemy((int) this.x, (int) this.y);
                bullet.tick();
                if (
                        bullet.getX() == this.x && bullet.getY() == this.y ||
                                bullet.getX() == this.x + 1 && bullet.getY() == this.y ||
                                bullet.getX() == this.x - 1 && bullet.getY() == this.y ||
                                bullet.getX() == this.x && bullet.getY() == this.y + 1 ||
                                bullet.getX() == this.x && bullet.getY() == this.y - 1
                        ) {
                    this.takeDamage(bullet.getDamage());
                    bullet.sethit();
                }
            }

            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).gethit()) {
                    bullets.remove(i);
                }

            }
        }
    }

    private void updateEnemyRatio() {
        enemyRatio = health /  (double) tempHealth;
    }

    private void increaseCounter() {
        healthIncrease++;
    }


    @Override
    public void tick() {
        now = System.nanoTime();

        advance();

        // Make sure it only get hit every ???
        if((now - lastTime) / 500000000 >= 1) {
            takeDamage((int) x, (int) y);
            lastTime = now;

        }
        if (!bullets.isEmpty()) {
            bulletHelper();
        }
        updateEnemyRatio();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rocks, (int) x, (int) y, width, height, null);
        g.setColor(Color.red);
        g.fillRect((int) x , (int) y  + height, width, height /4);
        g.setColor(Color.green);
        // fix ratio
            g.fillRect((int) x, (int) y + height, (int) (enemyRatio * width), height /4 );
        for(Bullet bullet : bullets)
            bullet.render(g);
    }
}
