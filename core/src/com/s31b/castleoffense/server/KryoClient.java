/*
 */
package com.s31b.castleoffense.server;

import com.esotericsoftware.kryonet.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fhict
 */
public class KryoClient {

    static Client client;
    static int tcpPort = 9999;
    static String serverIp = "localhost";

    public KryoClient() {
        client = new Client();
        registerPackets();

    }

    private void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                innerConnect();
            }
        }).start();
    }

    private void innerConnect() {
        try {
            client.connect(5000, serverIp, tcpPort);
            System.out.println("Connected!");
        } catch (IOException ex) {
            System.out.println("Reconnecting in 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex1);
            }
            innerConnect();
        }
    }

    public void send(TestPacket o) {
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
                TestPacket p = new TestPacket();
                kClient.send(p);
            } catch (IOException ex) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
