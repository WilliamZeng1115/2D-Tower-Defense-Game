package Entity;

import Worlds.Tile;
import Worlds.World;
import Graphics.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William1115 on 2016-06-02.
 */
public class Tower extends NonPlayer {

    private Tile nextTileUp = null;
    private Tile nextTileDown = null;
    private Tile nextTileRight = null;
    private Tile nextTileLeft = null;
    private Tile nextTileRightUp = null;
    private Tile nextTileRightDown = null;
    private Tile nextTileLeftUp = null;
    private Tile nextTileLeftDown = null;
    private ArrayList<Bullet> bullets = null;
    private World world;;
    private long ticker = 0;
    private Enemy target = null;
    private float enemyX, enemyY;
    private int cost;

    public Tower(World world, float x, float y) {
        super(x, y, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
        this.world = world;
        cost = DEFAULT_COST;

      /*  nextTileUp = world.getTile((int) ( x ), (int) (y- 1));
        nextTileDown = world.getTile((int) ( x / DEFAULT_TILE_WIDTH), (int) (y / DEFAULT_TILE_HEIGHT)+ 1);
        nextTileRight = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) + 1, (int) (y / DEFAULT_TILE_HEIGHT));
        nextTileLeft = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) - 1, (int) (y / DEFAULT_TILE_HEIGHT));

        nextTileRightUp = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) + 1, (int)(y / DEFAULT_TILE_HEIGHT)- 1);
        nextTileRightDown = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) + 1, (int) (y / DEFAULT_TILE_HEIGHT)+ 1);
        nextTileLeftUp = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) - 1, (int) (y / DEFAULT_TILE_HEIGHT)- 1);
        nextTileLeftDown = world.getTile((int) ( x / DEFAULT_TILE_WIDTH) - 1, (int) (y / DEFAULT_TILE_HEIGHT)+ 1);

        if(nextTileUp != null) {

            setTileUp(true);
        }

        if(nextTileDown != null) {
            setTileDown(true);

        }

        if(nextTileRight != null) {

            setTileRight(true);
        }

        if(nextTileLeft != null) {
            setTileLeft(true);
        }

        if(nextTileLeftUp != null) {
            setTileLeftUp(true);
        }

        if(nextTileLeftDown != null) {
            setTileLeftDown(true);

        }

        if(nextTileRightUp != null) {

            setTileRightUp(true);

        }

        if(nextTileRightDown != null) {
            setTileRightDown(true);
        }*/

        bullets = new ArrayList<Bullet>();
    }

/*
    public void setTileUp(Boolean damagebool) {

        //nextTileUp.setDamage(damagebool);
        if (nextTileUp.getId() == 0){
            world.setTileToDamage((int)x,(int) y -1);
        }
    }

    public void setTileDown(Boolean damagebool) {
        //nextTileDown.setDamage(damagebool);
        if (nextTileDown.getId() == 0){
            world.setTileToDamage((int) x, (int) y + 1);}
        //nextTileDown.setDamageNow(1);
    }

    public void setTileRight(Boolean damagebool) {
        //nextTileRight.setDamage(damagebool);
        if (nextTileRight.getId() == 0)
            world.setTileToDamage((int) x + 1, (int) y);
        // nextTileRight.setDamageNow(1);
    }

    public void setTileLeft(Boolean damagebool) {


        nextTileLeft.setDamage(damagebool);
        if (nextTileLeft.getId() == 0)
            world.setTileToDamage((int)x-1,(int) y);
        // nextTileLeft.setDamageNow(1);

    }

    public void setTileLeftUp(Boolean damagebool) {

        if (nextTileLeftUp.getId() == 0)
            world.setTileToDamage((int) x - 1, (int) y - 1);
        //nextTileLeftUp.setDamageNow(1);

    }

    public void setTileLeftDown(Boolean damagebool) {


        if (nextTileLeftDown.getId() == 0)
            world.setTileToDamage((int)x-1,(int) y+1);

    }

    public void setTileRightUp(Boolean damagebool) {


        if (nextTileRightUp.getId() == 0)
            world.setTileToDamage((int)x+1,(int) y-1);

    }

    public void setTileRightDown(Boolean damagebool) {


        if (nextTileRightDown.getId() == 0)
            world.setTileToDamage((int)x+1,(int) y+1);

    }*/


    public void getFirstEnemyX(ArrayList<Enemy> enemy) {

        if(!enemy.isEmpty())
            for (Enemy  e: enemy) {
                if(((x -100 < e.getX()) &&(x +100 > e.getX())&&((y -100 < e.getY()) &&(y +100 > e.getY())))) {

                    target = e;
                    break;
                }}
        if (target != null)
            enemyX = target.getX();

    }

    public void getFirstEnemyY(ArrayList<Enemy> enemy) {

        if (target != null)
            enemyY = target.getY();
    }

    public int getCost() {
        return cost;
    }


    @Override
    public void tick() {
        ticker += 1;


        if (target != null && ticker >60){
            ticker = 0;
            Bullet bullet = new Bullet((x-1)  , (y-1));
            target.addBullet(bullet);

        }



    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tower, (int) x, (int) y, width, height, null);


    }

}
