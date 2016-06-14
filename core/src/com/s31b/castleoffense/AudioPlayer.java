/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

import com.badlogic.gdx.audio.Music;

/**
 *
 * @author Shaikun
 */
public class AudioPlayer {

    private static Music AUDIO_PLAYER;
    private static float lastVolume;

    public static void play(String name) {
        checkPlaying();
        if (!Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setLooping(false);
            AUDIO_PLAYER.play();
        }
    }

    /**
     *
     * @param name
     * @param volume number between 1 and 100
     */
    public static void play(String name, float volume) {
        checkPlaying();
        if (volume > 0 && volume < 100 && !Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setLooping(false);
            AUDIO_PLAYER.setVolume(volume);
            AUDIO_PLAYER.play();
        }
    }

    public static void loop(String name) {
        checkPlaying();
        if (!Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setLooping(true);
            AUDIO_PLAYER.play();
        }
    }

    public static void loop(String name, float volume) {
        checkPlaying();
        if (volume > 0 && volume < 100 && !Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setVolume(volume);
            AUDIO_PLAYER.setLooping(true);
            AUDIO_PLAYER.play();

        }
    }

    private static void checkPlaying() {
        if (AUDIO_PLAYER != null && (AUDIO_PLAYER.isPlaying() || AUDIO_PLAYER.isLooping())) {
            AUDIO_PLAYER.dispose();
        }
    }
}
