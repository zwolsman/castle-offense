/*
 */
package com.s31b.castleoffense.server;

import com.s31b.castleoffense.game.entity.Defensive;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Marvin Zwolsman
 */
public interface ICoGame extends Remote {
    
    /**
     * Starts the game
     */
    void startGame() throws RemoteException;

    /**
     * Pauses the game
     */
    public void pauseGame() throws RemoteException;

    /**
     * Restarts the game
     */
    public void restartGame() throws RemoteException;

    /**
     * Ends the game
     */
    public void endGame() throws RemoteException;
    
    /**
     * Adds a tower to the game
     * @param tower to add
     */
    void addTower(Defensive tower) throws RemoteException;
    
}
