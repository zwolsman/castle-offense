///*
// */
//package com.s31b.castleoffense.server;
//
//import com.s31b.castleoffense.game.CoGame;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author fhict
// */
//public class Server {
//    
//    private static Server instance;
//    
//    public static final int PORT = 1099;
//    public static InetAddress HOST;
//    private Registry registry = null;
//    
//    private CoGame game;
//    private static final String BINDINGNAME = "GameInstance";
//
//    public Server() {
//        // Print port number for registry
//        System.out.println("Server: Port number " + PORT);
//
//        // Create Effectenbeurs
//        try {
//            game = new CoGame(1);
//            System.out.println("Server: Game created");
//        } catch (RemoteException ex) {
//            System.out.println("Server: Cannot create new Game");
//            System.out.println("Server: RemoteException: " + ex.getMessage());
//            game = null;
//        }
//        
//        try {
//            registry = LocateRegistry.createRegistry(PORT);
//            System.out.println("Server: Registry created on port number " + PORT);
//        } catch (RemoteException ex) {
//            System.out.println("Server: Cannot create registry");
//            System.out.println("Server: RemoteException: " + ex.getMessage());
//            registry = null;
//        }
//        
//        // Bind Game using registry
//        try {
//            registry.rebind(BINDINGNAME, game.getPublisher());
//        } catch (RemoteException ex) {
//            System.out.println("Server: Cannot bind Game");
//            System.out.println("Server: RemoteException: " + ex.getMessage());
//        }
//        
//        instance = this; // to ensure the singleton pattern
//    }
//
//    public static void main(String[] args) {
//        instance = new Server();
//        
//    }
//    
//    public Server getInstance() {
//        return this.instance;
//    }
//}
