package Entity;


/**
 * Created by William1115 on 2016-06-02.
 */
public abstract class NonPlayer implements Entity {

    public static final int DEFUALT_DAMAGE = 1;
    public static final int DEFAULT_ARMOR = 10;
    public static final int DEFAULT_COST = 10;

    protected int health;
    protected int gold;

    protected float x, y;
    protected int width, height;

    public NonPlayer(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        gold = DEFAULT_GOLD;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


}
