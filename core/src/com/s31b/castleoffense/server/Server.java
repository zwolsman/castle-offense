/*
 */
package com.s31b.castleoffense.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fhict
 */
public class Server {

    public static final int PORT = 1099;
    public static InetAddress HOST;
    private Registry registry = null;

    public Server() {
        try {
            HOST = InetAddress.getLocalHost();
            registry = LocateRegistry.createRegistry(PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

    }

}
