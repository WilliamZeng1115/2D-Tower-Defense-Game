package Worlds;

import Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by William1115 on 2016-05-05.
 */
public class DamageTile extends Tile {

    public DamageTile(int id) {
        super(Assets.rocks, id);
        setDamageNow(1);
        setDamage(true);
        setSolid(false);
        cost = 1000;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }
}
