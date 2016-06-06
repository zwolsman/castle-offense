/*
 */
package com.s31b.castleoffense.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.*;
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
    static ArrayList<ServerGame> games = new ArrayList<>();

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
        System.out.println(obj);
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
            games.get(packet.gid).broadcast(plPacket);
        }

        if (obj instanceof StartGamePacket) {
            StartGamePacket packet = (StartGamePacket) obj;
            startGame(packet.gid);
        }
        if (obj instanceof BuyTowerPacket) {
            BuyTowerPacket packet = (BuyTowerPacket) obj;
            ServerGame game = getServerGame(connection);
            if (game == null) {
                return;
            }
            Player p = game.getPlayer(connection);
            BoughtTowerPacket boughtPacket = new BoughtTowerPacket(packet.getX(), packet.getY(), p.getId(), packet.getName());
            game.broadcast(boughtPacket);
        }

        if (obj instanceof EndWavePacket) {
            EndWavePacket packet = (EndWavePacket) obj;
            ServerGame game = getServerGame(connection);

            if (game == null) {
                return;
            }
            Player p = game.getPlayer(connection);
            packet.pid = p.getId();
            game.broadcast(packet);
        }

        if (obj instanceof WinGamePacket) {
            WinGamePacket packet = (WinGamePacket) obj;
            ServerGame game = getServerGame(connection);
            if (game == null) {
                return;
            }
            game.broadcast(packet);
        }
    }

    /**
     * Get the server game that has the connection
     *
     * @param c Connection
     * @return ServerGame if found, null if none found
     */
    private ServerGame getServerGame(Connection c) {
        for (ServerGame g : games) {
            System.out.println("Checking game");
            if (g.isInGame(c)) {
                return g;
            }
        }
        System.out.println("No game found for " + c.getRemoteAddressTCP().getHostString());

        return null;
    }

    private void startGame(int id) {
        final ServerGame game = games.get(id);
        new Thread(() -> {
            new HeadlessApplication(game);
        }).start();
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection to a client.");

    }

    private void registerPackets() {
        server.getKryo().register(NewPlayerPacket.class
        );
        server
                .getKryo().register(NewPlayerResponsePacket.class
                );
        server
                .getKryo().register(StartGamePacket.class
                );

        server
                .getKryo().register(JoinGamePacket.class
                );
        server
                .getKryo().register(JoinedGamePacket.class
                );

        server
                .getKryo().register(CreateGamePacket.class
                );
        server
                .getKryo().register(CreatedGamePacket.class
                );

        server
                .getKryo().register(PlayerListPacket.class
                );
        server
                .getKryo().register(java.util.ArrayList.class
                );

        server
                .getKryo().register(BuyTowerPacket.class
                );
        server
                .getKryo().register(BoughtTowerPacket.class
                );

        server
                .getKryo().register(EndWavePacket.class
                );
        server
                .getKryo().register(WinGamePacket.class
                );
    }
}
