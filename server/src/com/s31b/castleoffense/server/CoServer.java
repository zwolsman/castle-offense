/*
 */
package com.s31b.castleoffense.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.BoughtTowerPacket;
import com.s31b.castleoffense.server.packets.BuyTowerPacket;
import com.s31b.castleoffense.server.packets.CreateGamePacket;
import com.s31b.castleoffense.server.packets.CreatedGamePacket;
import com.s31b.castleoffense.server.packets.EndWavePacket;
import com.s31b.castleoffense.server.packets.JoinGamePacket;
import com.s31b.castleoffense.server.packets.JoinedGamePacket;
import com.s31b.castleoffense.server.packets.NewPlayerPacket;
import com.s31b.castleoffense.server.packets.NewPlayerResponsePacket;
import com.s31b.castleoffense.server.packets.PlayerListPacket;
import com.s31b.castleoffense.server.packets.StartGamePacket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fhict
 */
public class CoServer extends Listener {

    static Server server;
    static int tcpPort = 9999;
    static ArrayList<ServerGame> games = new ArrayList<ServerGame>();

    public CoServer() {
        server = new Server();
        try {
            server.bind(tcpPort);
        } catch (IOException ex) {
            Logger.getLogger(KryoNetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        registerPackets();
        server.addListener(this);
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.start();
                System.out.println("Started server!");
            }
        }).start();
    }

    @Override
    public void connected(Connection connection) {
        System.out.println("Connection made with ip: " + connection.getRemoteAddressTCP().getHostString());
    }

    @Override
    public void received(Connection connection, Object obj) {

        if (obj instanceof CreateGamePacket) {
            System.out.println("Creating game. Requested ip: " + connection.getRemoteAddressTCP().getHostString());
            ServerGame g = new ServerGame(games.size());
            g.addPlayer(connection, "creator");
            games.add(g);
            connection.sendTCP(new PlayerListPacket("creator"));
            System.out.println("Created game with id: " + g.getGame().getId());
        }

        if (obj instanceof JoinGamePacket) {

            JoinGamePacket packet = (JoinGamePacket) obj;

            if (packet.gid > games.size()) {
                System.out.println("No game found with id!");
                return;
            }
            games.get(packet.gid).addPlayer(connection, "joiner");
            PlayerListPacket plPacket = new PlayerListPacket();
            for (Player p : games.get(packet.gid).getGame().getPlayers()) {
                plPacket.players.add(p.getName());
            }
            for (Connection c : games.get(packet.gid).getConnections()) {
                c.sendTCP(plPacket);
            }
        }

        if (obj instanceof StartGamePacket) {
            StartGamePacket packet = (StartGamePacket) obj;
            startGame(packet.gid);
        }
        if (obj instanceof BuyTowerPacket) {
            BuyTowerPacket packet = (BuyTowerPacket) obj;
            for (ServerGame g : games) {
                System.out.println("Checking game");
                if (g.isInGame(connection)) {
                    System.out.println("Found game with connection!");
                    Player p = g.getPlayer(connection);
                    BoughtTowerPacket boughtPacket = new BoughtTowerPacket(packet.x, packet.y, p.getId(), packet.name);
                    for (Connection c : g.getConnections()) {
                        c.sendTCP(boughtPacket);
                    }
                }
            }
        }

        if (obj instanceof EndWavePacket) {
            EndWavePacket packet = (EndWavePacket) obj;
            for (ServerGame g : games) {
                System.out.println("Checking game");
                if (g.isInGame(connection)) {
                    System.out.println("Found game with connection!");
                    Player p = g.getPlayer(connection);
                    packet.pid = p.getId();
                    for (Connection c : g.getConnections()) {
                        c.sendTCP(packet);
                    }
                }
            }
        }
    }

    private void startGame(int id) {
        final ServerGame game = games.get(id);
        new Thread(new Runnable() {
            @Override
            public void run() {

                new HeadlessApplication(game);
            }
        }).start();
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection to a client.");
    }

    private void registerPackets() {
        server.getKryo().register(NewPlayerPacket.class);
        server.getKryo().register(NewPlayerResponsePacket.class);
        server.getKryo().register(StartGamePacket.class);

        server.getKryo().register(JoinGamePacket.class);
        server.getKryo().register(JoinedGamePacket.class);

        server.getKryo().register(CreateGamePacket.class);
        server.getKryo().register(CreatedGamePacket.class);

        server.getKryo().register(PlayerListPacket.class);
        server.getKryo().register(java.util.ArrayList.class);

        server.getKryo().register(BuyTowerPacket.class);
        server.getKryo().register(BoughtTowerPacket.class);

        server.getKryo().register(EndWavePacket.class);

    }
}
