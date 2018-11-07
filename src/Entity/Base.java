package Entity;

import java.awt.*;

/**
 * Created by William1115 on 2016-06-02.
 */
public class Base extends NonPlayer {

    private int baseRatio;

    public Base(float x, float y, int width, int height) {
        super(x, y, width, height);
        health = health * 10;
        baseRatio = health / width;
    }

    public void decrementHealth(int damage){
        health = health - damage;
    }


    public int getHealth() {
        return health;
    }
    // Use tick to set a default tower to tick its tower (Can be upgraded)
    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y + height, width, height / 4);
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y + height, health / baseRatio, height / 4);
        g.setColor(Color.blue);
    }
}
