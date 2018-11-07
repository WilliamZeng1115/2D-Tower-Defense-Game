package Worlds;

import Graphics.Assets;

/**
 * Created by William1115 on 2016-05-02.
 */
public class GroundTile extends Tile {

    public GroundTile(int id) {
        super(Assets.ground, id);
        cost = 1000;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }
}
