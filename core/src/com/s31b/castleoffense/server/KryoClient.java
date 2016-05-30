/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.server.packets.BoughtTowerPacket;
import com.s31b.castleoffense.server.packets.BuyTowerPacket;
import com.s31b.castleoffense.server.packets.CreateGamePacket;
import com.s31b.castleoffense.server.packets.CreatedGamePacket;
import com.s31b.castleoffense.server.packets.EndWavePacket;
import com.s31b.castleoffense.server.packets.IPacket;
import com.s31b.castleoffense.server.packets.JoinGamePacket;
import com.s31b.castleoffense.server.packets.JoinedGamePacket;
import com.s31b.castleoffense.server.packets.NewPlayerPacket;
import com.s31b.castleoffense.server.packets.NewPlayerResponsePacket;
import com.s31b.castleoffense.server.packets.PlayerListPacket;
import com.s31b.castleoffense.server.packets.StartGamePacket;
import java.io.IOException;

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

    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.start();
                innerConnect();
            }
        }).start();
    }

    public Client getClient() {
        return this.client;
    }

    private Boolean innerConnect() {
        try {
            client.connect(5000, serverIp, tcpPort);
            System.out.println("Connected!");
            return true;
        } catch (IOException ex) {
            try {
                //Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
                Thread.sleep(5000);
            } catch (InterruptedException ex1) {
                // Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return innerConnect();
        }
    }

    public void send(IPacket o) {
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

    }

    @Override
    public void connected(Connection connection) {
        System.out.println("Connected to server with ip: " + connection.getRemoteAddressTCP().getHostString());
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection with server: " + connection.getRemoteAddressTCP().getHostString());
    }

    @Override
    public void received(Connection connection, Object obj) {
        /*if (obj instanceof TestPacket) {
            TestPacket t = (TestPacket) obj;
            System.out.println(t.msg);
        }*/
    }
}
