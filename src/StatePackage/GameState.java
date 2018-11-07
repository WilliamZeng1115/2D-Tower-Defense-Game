package StatePackage;
import java.awt.*;

import Entity.*;
import Launcher.Game;
import Launcher.InGame;
import Worlds.World;

/**
 * Created by William on 2016-04-27.
 */
public class GameState extends State {

    private Player player;
    // take the list of enemies from player and tick/render it
    private World world;
    private InGame inGame;


    public GameState(Game game) {
        super(game);
        world = new World("res/Texture/World4.txt");
    }


    public void setPlayer(Player player) {
        this.player = player;
        player.setWorld(world);
    }

    public void setInGame(InGame inGame) {
        this.inGame = inGame;
    }



    @Override
    public void tick() {
        inGame.tick();
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        // maybe render the list of enemy from the player
        inGame.render(g);
        world.render(g);
        player.render(g);


        // render out the different type of towers possible
    }

    public World getWorld() {
        return world;
    }
}
