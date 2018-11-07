package Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by William on 2016-04-27.
 */
public class Assets {

    public static final int WIDTH = 32, HEIGHT = 32;

    public static BufferedImage ground, wall, rocks, tower, bullets;

    // Singleton
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Texture/Untitled.png"));

        ground = sheet.crop(0, 0, WIDTH, HEIGHT);
        wall = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        rocks = sheet.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);
        tower =  sheet.crop(WIDTH * 3, HEIGHT * 2, WIDTH, HEIGHT);
        bullets = sheet.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);

    }
}
