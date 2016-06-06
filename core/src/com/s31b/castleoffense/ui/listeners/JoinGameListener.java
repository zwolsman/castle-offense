/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.JoinGamePacket;
import java.util.Scanner;

/**
 *
 * @author Nick
 */
public class JoinGameListener extends ClickListener {
    @Override
    public void clicked(InputEvent event, float x, float y) {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        System.out.println("Enter the id of the game:");
        Scanner sc = new Scanner(System.in);

        int nextId = 0;//sc.nextInt();
        System.out.println("Trying to join game " + nextId);
        joinGame(nextId);

    }
    
    private void joinGame(int id) {
        Globals.client.send(new JoinGamePacket(id));
    }
}
