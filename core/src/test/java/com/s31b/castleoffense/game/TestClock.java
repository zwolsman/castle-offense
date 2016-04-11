/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.s31b.castleoffense.game.Clock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dennis
 */
public class TestClock {
    
    @Before
    public void setDelta(){
        
        Clock.multiplier = 1;
        Clock.d = 0;
        Clock.lastFrame = 0;
        Clock.totalTime = 0;
        Clock.getDelta();
    }
    
    @Test
    public void TestGetTime(){
        assertEquals(System.currentTimeMillis() * 1000, Clock.getTime()); 
    }
    
    @Test
    public void TestGetDelta() throws InterruptedException{
        /*float startExpected1 = 100;
        float endExpected1   =  111;
        float startDelta1 = Clock.getDelta();
        Thread.sleep(100);
        float deltaResult1 = Clock.getDelta();
        
        assertEquals("Start delta is not 0", 0, (int)startDelta1);
        
        if(deltaResult1 > endExpected1){
             fail(deltaResult1 + " is higher than "+ endExpected1 );
        }
        if(deltaResult1 < startExpected1){
            fail(deltaResult1 + " is lower than or same as "+ startExpected1 );
        }
        */
        
    }
    
    @Test
    public void TestDelta() throws InterruptedException{
        ///Test Delta with multiplier 1
        Clock.Update();
        Thread.sleep(200);
        Clock.Update();
        float startDelta1 = Clock.Delta();
        float deltaExpected1 = Clock.d;
        
        assertEquals("multiplier 1", deltaExpected1, startDelta1, 0);
        
        ///Test Delta with multiplier 3 (Start = 1 / Add 2 multipliers)
        Clock.ChangeMultiplier(2);//Add 2
        Clock.Update();
        Thread.sleep(200);
        Clock.Update();
        float startDelta2 = Clock.Delta();
        Thread.sleep(200);
        float deltaExpected2 = Clock.d * 3;
        
        assertEquals("multiplier 3", deltaExpected2, startDelta2, 0);
    }
    
    @Test
    public void TestMultiplier(){
        ///Test multiplier add 2
        
        float start1 = Clock.Multiplier();
        
        Clock.ChangeMultiplier(2);
        float expected1 = start1 + 2;
        float result1 = Clock.Multiplier();
        assertEquals(expected1, result1, 0);
        
        
        //Test multiplier with 8 this will do nothings
        float start2 = Clock.Multiplier();
        
        Clock.ChangeMultiplier(8);
        float expected2 = start2;
        float result2 = Clock.Multiplier();
        assertEquals(expected2, result2, 0);
        
        //Test multiplier with min value
        Clock.multiplier = 5;
        
        Clock.ChangeMultiplier(-3);
        float result3 = Clock.Multiplier();
        assertEquals(2, result3, 0);
        
         //Test multiplier with min value go under 0
        Clock.multiplier = 5;
        
        Clock.ChangeMultiplier(-6);
        float result4 = Clock.Multiplier();
        assertEquals(5, result4, 0);
        
    }
    
    @Test
    public void TestUpdate(){
        Clock.Update();
        float expected1 = Clock.d;
        assertEquals(expected1, Clock.totalTime, 0);
        
        Clock.Update();
        float expected2 = Clock.d + expected1;
        assertEquals(expected2, Clock.totalTime, 0);
    }
    
    @Test
    public void TestPause(){
        Clock.Update();
        Clock.Update();
        Clock.Pause();
        
        assertEquals(0, Clock.Delta(), 0);
        
        Clock.Pause();
        Float expected2 = Clock.d;
        
        assertEquals(expected2, Clock.Delta(), 0);
        
    }
}
