/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
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

        if (obj instanceof TestPacket) {
            TestPacket tPacket = (TestPacket) obj;
            System.out.println("Test packet msg: " + tPacket.msg);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Lost connection to a client.");
        // System.out.println("Lost connection with ip: " + connection.getRemoteAddressTCP().getHostString());

    }

    private void registerPackets() {
        server.getKryo().register(TestPacket.class);
    }

    public static void main(String[] args) {
        new KryoNetServer().start();
        while (true) {
        }
    }
}
