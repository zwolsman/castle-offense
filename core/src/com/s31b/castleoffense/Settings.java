/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

/**
 *
 * @author Shaikun
 */
public class Settings {

    private static Settings instance;
    private static boolean mute = false;

    protected Settings() {
        //
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public boolean isMuted() {
        return mute;
    }

    public void toggleMute() {
        mute = !mute;
        AudioPlayer.mute();
    }
}
