package Launcher;

import Entity.Player;
import Entity.Tower;
import StatePackage.GameState;
import StatePackage.StateManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by William1115 on 2016-07-01.
 */
public class InGame extends MouseAdapter {
    private Game game;
    private GameState gameState;
    private int mx, my;
    private Player player;
    private Tower tower;

    public InGame(Game game) {
        this.game = game;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        tower = new Tower(gameState.getWorld(), 110, 560);
    }

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        if(mouseOver(125, 700, 150, 50) && StateManager.getCurrentState().equals(gameState)) {
            StateManager.setCurrentState(game.getMenuState());
        }

        if(mouseOver(420 ,700, 150, 50) && StateManager.getCurrentState().equals(gameState)) {
            StateManager.setCurrentState(game.getShopState());
        }

        if(mouseOver(700 ,700, 150, 50) && StateManager.getCurrentState().equals(gameState)) {
            System.exit(0);
        }

        if(mouseOver(50 ,530, 170, 125) && StateManager.getCurrentState().equals(gameState)) {
            player.setCurrentTowerState(tower);
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    private boolean mouseOver(int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height)
                return true;

        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font f = new Font("arial", 1, 50);
        Font f1 = new Font("arial", 1, 35);

        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.setColor(Color.white);
        g.drawRect(125 ,700, 150, 50);


        g.setColor(Color.white);
        g.drawRect(420 ,700, 150, 50);


        g.setColor(Color.white);
        g.drawRect(700 ,700, 150, 50);

        g.setColor(Color.white);
        g.drawRect(50 ,530, game.getWidth() - 142, 125);

        g.setFont(f1);
        g.setColor(Color.green);
        g.drawString("Menu", 150, 740);

        g.drawString("Tower", 80, 570);

        g.setFont(f1);
        g.setColor(Color.green);
        g.drawString("Upgrade", 425, 740);

        g.setFont(f1);
        g.setColor(Color.green);
        g.drawString("Quit", 735, 740);

        g.setColor(Color.WHITE);
        g.drawLine(0, 500, game.getWidth(), 500);

        g.drawLine(220, 530, 220, 655);
        g.drawLine(390, 530, 390, 655);
        g.drawLine(560, 530, 560, 655);
        g.drawLine(730, 530, 730, 655);
        g.drawLine(900, 530, 900, 655);

    }
}
