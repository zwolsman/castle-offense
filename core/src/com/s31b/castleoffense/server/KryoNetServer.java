/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.s31b.castleoffense.server.packets.IPacket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fhict
 */
public class KryoNetServer extends Listener {

    static Server server;
    static int tcpPort = 9999;

    public KryoNetServer() {
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
        System.out.println("Received packet from ip: " + connection.getRemoteAddressTCP().getHostString());
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection to a client.");
    }

    private void registerPackets() {
        //server.getKryo().register(TestPacket.class);
        //TODO register all the packets
    }

    public void broadcast(IPacket p) {
        System.out.println("Connections: " + server.getConnections().length);
        for (int i = 0; i < server.getConnections().length; i++) {
            Connection c = server.getConnections()[i];
            c.sendTCP(p);
            System.out.println("Send packet to: " + c.getRemoteAddressTCP().getHostString());
        }
    }

    public static void main(String[] args) {
        KryoNetServer server = new KryoNetServer();
        server.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String msg = reader.readLine();
                if ("q".equals(msg.toLowerCase())) {
                    System.exit(0);
                }
                /*TestPacket p = new TestPacket();
                p.msg = msg;
                server.broadcast(p);*/
            } catch (IOException ex) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
