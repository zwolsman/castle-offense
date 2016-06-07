/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

/**
 *
 * @author Shaikun
 */
public class AudioFactory {

    private static final HashMap cache = new HashMap();

    public static Sound getSound(String soundName) {
        if (!cache.containsKey(soundName)) {
            cache.put(soundName, Gdx.audio.newSound(Gdx.files.internal(soundName)));
        }
        return (Sound) cache.get(soundName);
    }

    public static Music getMusic(String soundName) {
        if (!cache.containsKey(soundName)) {
            cache.put(soundName, Gdx.audio.newMusic(Gdx.files.internal(soundName)));
        }
        return (Music) cache.get(soundName);
    }
}
