/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.game;

import com.badlogic.gdx.utils.TimeUtils;
/**
 *
 * @author Cornee Geenen
 */
public class Clock {
    private static boolean paused = false;
    public static long lastFrame;
    public static long totalTime;
    public static float d = 0;
    public static float multiplier;
    
    public static long getTime(){
        return System.currentTimeMillis() * 1000;
    }
    
    public static float getDelta(){
        long currentTime = getTime();
        int delta = (int)(currentTime - lastFrame);
        lastFrame = getTime();
        return delta * 0.01f; 
    }
    
    public static float Delta(){
        if(paused)return 0;
        else return d * multiplier; 
    }
    
    public static float TotalTime(){
        return totalTime;
    }
    
    public static float Multiplier(){
        return multiplier;
    }
    
    public static void Update(){
        d = getDelta();
        totalTime += d;
    }
    
    public static void ChangeMultiplier(int change){
        if(multiplier + change > -1 && multiplier + change < 7){
           multiplier += change;
        }
    }
    
    public static void Pause(){
        if(paused){
            paused = false;   
        }else{
            paused = true;
        }
    }
}
