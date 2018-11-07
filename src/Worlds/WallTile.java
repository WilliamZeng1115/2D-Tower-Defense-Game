package Worlds;

import Graphics.Assets;

/**
 * Created by William1115 on 2016-05-02.
 */
public class WallTile extends Tile {

    public WallTile(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        setSolid(true);
        return solid;
    }


}
