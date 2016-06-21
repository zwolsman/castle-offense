/*
 */
package com.s31b.castleoffense.server;

import com.badlogic.gdx.ApplicationListener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.IPacket;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author fhict
 */
public class ServerGame extends Listener implements ApplicationListener {

    private CoGame game;
    private String name;
    private final HashMap<Connection, Player> players = new HashMap<>();

    public ServerGame(int id, String name) {
        this.game = new CoGame(id);
        this.name = name;
    }

    public int getId() {
        return game.getId();
    }

    public ServerGame() {
    }

    public void addPlayer(Connection c, String name) {
        players.put(c, game.addPlayer(name));
    }

    /**
     * Will check if specified connection is in the game
     *
     * @param c a connection
     * @return
     */
    public Boolean isInGame(Connection c) {
        return players.containsKey(c);
    }

    /**
     * Gets the name of for in the lobby
     *
     * @return the name of the game
     */
    public String getName() {
        return this.name;
    }

    public Player getPlayer(Connection c) {
        return players.get(c);
    }

    public Collection<Connection> getConnections() {
        return players.keySet();
    }

    /**
     * Send a packet to all the connected players
     *
     * @param packet Packet to send
     */
    public void broadcast(IPacket packet) {
        for (Connection c : getConnections()) {
            c.sendTCP(packet);
        }
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
    }

    @Override
    public void render() {
        game.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    List<Player> getPlayers() {
        return game.getPlayers();
    }

}
