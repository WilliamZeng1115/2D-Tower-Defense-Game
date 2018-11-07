package Entity;

import Worlds.Tile;

import java.awt.*;

/**
 * Created by William1115 on 2016-06-02.
 */
public interface Entity {
    public static final int DEFAULT_TILE_WIDTH = Tile.getTILEWIDTH();

    public static final int DEFAULT_TILE_HEIGHT = Tile.getTILEHEIGHT();

    public static final int DEFAULT_HEALTH = 10;

    public static final int DEFAULT_GOLD = 100;

    public abstract void tick();

    public abstract void render(Graphics g);
}
