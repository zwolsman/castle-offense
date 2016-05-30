/*
 */
package com.s31b.castleoffense.server;

import com.badlogic.gdx.ApplicationListener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.player.Player;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author fhict
 */
public class ServerGame extends Listener implements ApplicationListener {

    private CoGame game;
    //private final ArrayList<Connection> connectedPlayers = new ArrayList<Connection>();

    private final HashMap<Connection, Player> players = new HashMap<Connection, Player>();

    public ServerGame(int id) {
        game = new CoGame(id);
    }

    public ServerGame() {
    }

    public void addPlayer(Connection c, String name) {
        //connectedPlayers.add(c);
        players.put(c, game.addPlayer(name));
    }

    /**
     * Will check if specified connection is in the game
     *
     * @param c a connection
     * @return
     */
    public Boolean isInGame(Connection c) {
        return players.containsKey(c); //return connectedPlayers.contains(c);
    }

    public Player getPlayer(Connection c) {
        return players.get(c);
    }

    public Collection<Connection> getConnections() {
        return players.keySet();
    }

    /**
     * Will check if the specified username is in the game
     *
     * @param name the in-game name of the player
     * @return
     */
    public Boolean isInGame(String name) {
        for (Player p : game.getPlayers()) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public CoGame getGame() {
        return game;
    }

    public void setGame(CoGame game) {
        this.game = game;
    }

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render() {
        game.update();
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
