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
 * @author Marvin Zwolsman
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
                p.msg = msg;
                kClient.send(p);
            } catch (IOException ex) {
                Logger.getLogger(KryoClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
