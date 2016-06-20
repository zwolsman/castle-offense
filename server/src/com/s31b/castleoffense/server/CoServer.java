package com.s31b.castleoffense.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Zwolsman
 */
public class CoServer extends Listener {

    static Server server;
    static int tcpPort = 9999;
    static ArrayList<ServerGame> games = new ArrayList<>();
    private static int id = 0;

    public static int getNextGameId() {
        return id++;
    }

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
        new Thread(() -> {
            server.start();
            System.out.println("Started server!");
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
            CreateGamePacket packet = (CreateGamePacket) obj;

            if (packet.getName().isEmpty()) {
                packet.setName("<NO NAME>");
            }
            ServerGame g = new ServerGame(getNextGameId(), packet.getName());
            g.addPlayer(connection, "creator");
            games.add(g);
            connection.sendTCP(new PlayerListPacket("creator"));
            return;
        }

        if (obj instanceof JoinGamePacket) {

            JoinGamePacket packet = (JoinGamePacket) obj;

            if (packet.gid > games.size()) {
                System.out.println("No game found with id!");
                return;
            }
            ServerGame sGame = getServerGame(packet.gid);

            if (sGame != null) {
                sGame.addPlayer(connection, "joiner");
                PlayerListPacket plPacket = new PlayerListPacket();
                for (Player p : getServerGame(packet.gid).getGame().getPlayers()) {
                    plPacket.players.add(p.getName());
                }
                getServerGame(packet.gid).broadcast(plPacket);

                for (Defensive tower : getServerGame(packet.gid).getGame().getAllTowers()) {
                    connection.sendTCP(new BoughtTowerPacket(tower.getPosition().getX(), tower.getPosition().getY(), tower.getId(), tower.getName()));
                }
                return;
            }
            return;
        }

        if (obj instanceof StartGamePacket) {
            StartGamePacket packet = (StartGamePacket) obj;
            startGame(packet.gid);
            return;
        }
        if (obj instanceof BuyTowerPacket) {
            BuyTowerPacket packet = (BuyTowerPacket) obj;
            ServerGame game = getServerGame(connection);
            if (game == null) {
                return;
            }
            Player p = game.getPlayer(connection);
            BoughtTowerPacket boughtPacket = new BoughtTowerPacket(packet.getX(), packet.getY(), p.getId(), packet.getName());

            Defensive tower = (Defensive) EntityFactory.buyEntity(EntityType.getTypeFromString(packet.getName()), p);
            tower.setPosition(new Tile(packet.getX(), packet.getY()));
            game.getGame().addTower(tower);

            game.broadcast(boughtPacket);
            return;
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
            return;
        }

        if (obj instanceof WinGamePacket) {
            WinGamePacket packet = (WinGamePacket) obj;
            ServerGame game = getServerGame(connection);
            if (game == null) {
                return;
            }
            game.broadcast(packet);

            games.remove(game);
            return;
        }

        if (obj instanceof RequestGameListPacket) {
            GameListPacket packet = new GameListPacket();
            int id = 0;
            for (ServerGame game : games) {
                packet.games.add(game.getName());
                packet.ids.add(id++);
                packet.max.add(2);
            }
            connection.sendTCP(packet);
            return;
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

    private ServerGame getServerGame(int id) {
        for (ServerGame g : games) {
            if (g.getId() == id) {
                return g;
            }
        }

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
        server
                .getKryo().register(GameListPacket.class
                );
        server
                .getKryo().register(RequestGameListPacket.class
                );

    }
}
