package Entity;

import java.awt.*;
import Graphics.*;
import Worlds.Tile;

/**
 * Created by William on 2016-04-27.
 */
public class Bullet extends NonPlayer {

    // different movements
    private int xMove, yMove;
    private int Damage = 10 ;
    private boolean hit= false;

    public Bullet(float x, float y) {
        super(x, y, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);

    }

    public void trackEnemy(float x, float y) {
        if(this.x > x)
            xMove = -2;
        else if(this.x < x)
            xMove = 2;
        if(this.y > y)
            yMove = -2;
        else if(this.y < y)
            yMove = 2;
    }

    public void move() {
        x += xMove;
        y += yMove;
    }

    public int getDamage() {
        return Damage;
    }

    public void sethit() {
        hit= true;
    }

    public boolean gethit() {
        return hit;
    }
    @Override
    public void tick() {
        move();
        xMove = 0;
        yMove = 0;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullets,(int) x , (int)y , width, height, null);
    }
}
