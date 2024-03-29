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
    private static float LAST_VOLUME;
    private static String PLAYING;
    private static Boolean LOOPING;

    public static void play(String name) {
        LOOPING = false;
        PLAYING = name;
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
        LOOPING = false;
        PLAYING = name;
        checkPlaying();
        if (volume > 0 && volume < 100 && !Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setLooping(false);
            AUDIO_PLAYER.setVolume(volume);
            AUDIO_PLAYER.play();
        }
    }

    public static void loop(String name) {
        LOOPING = true;
        PLAYING = name;
        checkPlaying();
        if (!Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setLooping(true);
            AUDIO_PLAYER.play();
        }
    }

    public static void loop(String name, float volume) {
        LOOPING = true;
        PLAYING = name;
        checkPlaying();
        if (volume > 0 && volume < 100 && !Settings.getInstance().isMuted()) {
            AUDIO_PLAYER = AudioFactory.getMusic(name);
            AUDIO_PLAYER.setVolume(volume);
            AUDIO_PLAYER.setLooping(true);
            AUDIO_PLAYER.play();
        }
    }

    public static void stop() {
        AUDIO_PLAYER.dispose();
    }

    public static void mute(Boolean mute) {
        if (mute) {
            if (AUDIO_PLAYER != null && (AUDIO_PLAYER.isPlaying() || AUDIO_PLAYER.isLooping())) {
                AUDIO_PLAYER.dispose();
            }
        } else if (PLAYING != null) {
            AUDIO_PLAYER = AudioFactory.getMusic(PLAYING);
            AUDIO_PLAYER.setLooping(LOOPING);
            AUDIO_PLAYER.play();
        }
    }

    private static void checkPlaying() {
        System.out.println(Settings.getInstance().isMuted());
        if (AUDIO_PLAYER != null && (AUDIO_PLAYER.isPlaying() || AUDIO_PLAYER.isLooping())) {
            AUDIO_PLAYER.dispose();
        }
    }
}
