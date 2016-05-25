/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Zwolsman
 */
public class KryoClient extends Listener {

    static Client client;
    static int tcpPort = 9999;
    static String serverIp = "145.93.131.82";

    public KryoClient() {
        client = new Client();
        registerPackets();
        client.addListener(this);
    }

    private void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.start();
                innerConnect();
            }
        }).start();
    }

    private Boolean innerConnect() {
        try {
            client.connect(5000, serverIp, tcpPort);
            System.out.println("Connected!");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);

            return innerConnect();
        }
    }

    public void send(Packet o) {
        client.sendTCP(o);
    }

    private void registerPackets() {
        client.getKryo().register(TestPacket.class);
    }

    public static void main(String[] args) {
        KryoClient kClient = new KryoClient();
        kClient.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String msg = reader.readLine();
                
                if (msg.equals("q")) System.exit(0);
                
                TestPacket p = new TestPacket();
                p.msg = msg;
                kClient.send(p);
            } catch (IOException ex) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        if (obj instanceof TestPacket) {
            TestPacket t = (TestPacket) obj;
            System.out.println(t.msg);
        }
    }
}
