package com.s31b.castleoffense.ui;

/**
 *
 * @author GoosLaptop
 */
public class StatusUpdate {
    /**
     * Log a status update message to the player
     * @param msg Message to show
     */
    public static void log (String msg) {
        GameMenu.setPlayerFeedback(msg);
    }
}
