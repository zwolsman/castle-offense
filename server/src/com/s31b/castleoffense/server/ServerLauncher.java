package com.s31b.castleoffense.server;

import java.util.Scanner;

public class ServerLauncher {

    private static ServerGame gameInstance = new ServerGame();

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        CoServer server = new CoServer();
        server.start();

        while (true) {
            if (scanner.nextLine().toLowerCase().equals("q")) {
                System.exit(0);
                break;
            }
        }
    }
}
