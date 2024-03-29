/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.server.packets.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Zwolsman
 */
public class KryoClient extends Listener {

    static Client client;
    static int tcpPort = 9999;
    static String serverIp = "localhost";

    public KryoClient() {
        client = new Client();
        registerPackets();
        client.addListener(this);
    }

    public void connect() {
        new Thread(() -> {
            client.start();
            try {
                innerConnect();
            } catch (InterruptedException ex) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public Client getClient() {
        return KryoClient.client;
    }

    private Boolean innerConnect() throws InterruptedException {
        try {
            client.connect(5000, serverIp, tcpPort);
            System.out.println("Connected!");
            return true;
        } catch (Exception ex) {
            Thread.sleep(5000);
            return innerConnect();
        }
    }

    public void send(IPacket o) {
        System.out.println("Sending packet " + o.getClass().getName());
        client.sendTCP(o);
    }

    private void registerPackets() {
        client.getKryo().register(NewPlayerPacket.class);
        client.getKryo().register(NewPlayerResponsePacket.class);
        client.getKryo().register(StartGamePacket.class);

        client.getKryo().register(JoinGamePacket.class);
        client.getKryo().register(JoinedGamePacket.class);

        client.getKryo().register(CreateGamePacket.class);
        client.getKryo().register(CreatedGamePacket.class);

        client.getKryo().register(PlayerListPacket.class);
        client.getKryo().register(java.util.ArrayList.class);

        client.getKryo().register(BuyTowerPacket.class);
        client.getKryo().register(BoughtTowerPacket.class);

        client.getKryo().register(EndWavePacket.class);
        client.getKryo().register(WinGamePacket.class);
        client.getKryo().register(GameListPacket.class);
        client.getKryo().register(RequestGameListPacket.class);

    }

    @Override
    public void connected(Connection connection) {
        System.out.println("Connected to server with ip: " + connection.getRemoteAddressTCP().getHostString());
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection with server");
    }
}
