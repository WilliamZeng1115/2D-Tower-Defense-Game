package Worlds;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by William1115 on 2016-05-02.
 */
public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile groundTile = new GroundTile(0);
    public static Tile wallTile = new WallTile(1);
    public static Tile damageTile = new DamageTile(2);

    protected BufferedImage texture;
    protected final int id;
    protected int x, y;
    protected boolean damage = false;
    protected boolean solid = false;
    protected int damageNow;
    protected int cost;

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        this.damage = false;
        tiles[id] = this;
        damageNow = 0;
        cost = 100000;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public void tick() {

    }

    public int getDamageNow() {
        return damageNow;
    }

    public void setDamageNow(int damageNow) {
        this.damageNow = damageNow;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(Boolean damage) {
        this.damage = damage;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(Boolean solid) {
        this.solid = solid;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public int getId() {
        return id;
    }

    public static int getTILEWIDTH() {
        return TILEWIDTH;
    }

    public static int getTILEHEIGHT() {
        return TILEHEIGHT;
    }

}
