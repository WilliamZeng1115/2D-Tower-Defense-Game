package Input;

import Launcher.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by William on 2016-04-28.
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean g, m, s, n;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        g = keys[KeyEvent.VK_G];
        m = keys[KeyEvent.VK_M];
        s = keys[KeyEvent.VK_S];
        n = keys[KeyEvent.VK_N];

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        keys[e.getKeyCode()] = false;

    }
}
