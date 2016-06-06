/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 *
 * @author Shaikun
 */
public class AudioPlayer {

    private static Music AUDIO_PLAYER;

    public static void play(String name) {
        AUDIO_PLAYER = Gdx.audio.newMusic(Gdx.files.internal(name));
        AUDIO_PLAYER.play();
    }

    /**
     *
     * @param name
     * @param volume number between 1 and 100
     */
    public static void play(String name, int volume) {
        if (volume > 0 && volume < 100) {
            AUDIO_PLAYER = Gdx.audio.newMusic(Gdx.files.internal(name));
            AUDIO_PLAYER.setVolume(((float) volume / 100));
            AUDIO_PLAYER.play();
        }
    }

    public static void loop(String name) {
        AUDIO_PLAYER = Gdx.audio.newMusic(Gdx.files.internal(name));
        AUDIO_PLAYER.setLooping(true);
        AUDIO_PLAYER.play();
    }

    public static void loop(String name, int volume) {
        if (volume > 0 && volume < 100) {
            AUDIO_PLAYER = Gdx.audio.newMusic(Gdx.files.internal(name));
            AUDIO_PLAYER.setVolume(((float) volume / 100));
            AUDIO_PLAYER.setLooping(true);
            AUDIO_PLAYER.play();
        }
    }
}
