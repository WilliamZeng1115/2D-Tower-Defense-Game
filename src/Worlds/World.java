package Worlds;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by William1115 on 2016-05-03.
 */
public class World {

    private int width, height;
    private int[][] tiles;
    private Tile[][] tileTemp;
    private int count;


    // spawn area
    private int spawnX, spawnY;

    public World(String path) {
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for(int y = 0; y < height; y++)  {
            for(int x = 0; x < width; x++) {

                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);

            }
        }

    }

    public void setTileToDamage(int x, int y) {
      // tiles[x][y] = 2;
       // Tile tempTile = tileTemp[x][y];
       // int tempCost = tempTile.getCost();
       // tileTemp[x][y] = new DamageTile(2);
       // tileTemp[x][y].setCost(tempCost);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x > width || y > height)
            return Tile.wallTile;
        Tile t = tileTemp[x][y];
        if(t == null)
            return Tile.wallTile;
        return t;
    }

    private void setAllCost(int spawnX, int spawnY) {
        int x = spawnX;
        int y = spawnY;

        Tile tile = this.getTile(x,y);

        int cost = tile.getCost();
        if( y> 0 && this.getTile(x, y-1).getCost() > cost + 1 && this.getTile(x, y-1).getCost() != 100000) {

            this.getTile(x, y-1).setCost(cost +1);

            setAllCost(x, y - 1);
        }
        if(y < height - 1 && this.getTile(x, y+1).getCost() > cost + 1 && this.getTile(x, y+1).getCost() != 100000) {

            this.getTile(x, y + 1).setCost(cost + 1);

            setAllCost(x, y + 1);
        }
        if(x < width - 1 && this.getTile(x - 1, y).getCost() > cost + 1 && this.getTile(x - 1, y).getCost() != 100000 ) {

            this.getTile(x - 1, y).setCost(cost + 1);

            setAllCost(x - 1, y);
        }
        if(x > 0 && this.getTile(x + 1, y).getCost() > cost + 1 && this.getTile(x + 1, y).getCost() != 100000) {

            this.getTile(x + 1, y).setCost(cost + 1);

           setAllCost(x + 1, y);
        }

    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);

        // split at all spaces
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        // now world building
        tiles = new int[width][height];
        tileTemp = new Tile[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                if(tiles[x][y] == 0) {
                    tileTemp[x][y] = new GroundTile(0);
                }
                else  if(tiles[x][y] == 1) {
                    tileTemp[x][y] = new WallTile(1);
                }
            }
        }

        tileTemp[spawnX][spawnY].setCost(0);
        setAllCost(spawnX, spawnY);
    }



    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
