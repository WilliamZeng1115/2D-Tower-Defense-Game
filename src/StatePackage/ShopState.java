package StatePackage;

import Entity.Player;
import Launcher.Game;
import Launcher.Shop;

import java.awt.*;

/**
 * Created by William on 2016-04-27.
 */
public class ShopState extends State {
    private Shop shop;


    public ShopState(Game game) {
        super(game);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void tick() {
        shop.tick();

    }

    @Override
    public void render(Graphics g) {
        shop.render(g);

    }
}
