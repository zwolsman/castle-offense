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

    private static boolean mute = false;

    public static boolean isMuted() {
        return mute;
    }

    public static void toggleMute() {
        mute = !mute;
    }

}
