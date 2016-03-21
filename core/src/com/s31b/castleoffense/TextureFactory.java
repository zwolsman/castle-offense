/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

/**
 *
 * @author fhict
 */
public class TextureFactory {
    
    private static final HashMap cache = new HashMap();
    
    public static Texture getTextture(String name) {
        if(!cache.containsKey(name))
        {
            cache.put(name, new Texture(name + ".png"));
        }
        return (Texture) cache.get(name);
    } 
}
