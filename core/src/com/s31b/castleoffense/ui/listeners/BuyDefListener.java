/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.BuyTowerPacket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick
 */
public class BuyDefListener extends ClickListener {
    private List<DefensiveDAO> defensives;
    private List<DefensiveDAO> defPerWaveList = new ArrayList<DefensiveDAO>();
    private String offensiveType;
    private CoGame game;
    private Player player;
    private Defensive towerToPlace;
    private String defensiveString;
    
    public BuyDefListener(CoGame g, String defensiveString, Player p){
        this.player = p;
        this.offensiveType = defensiveString;
        this.defensives = EntityFactory.getAllDefensives();
        this.game = g;
        this.defensiveString = defensiveString;
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (player != null && game != null) {
            for (DefensiveDAO d : defensives) {
                if (defensiveString.equals(d.getName())) {
                    towerToPlace = (Defensive) EntityFactory.buyEntity(EntityType.valueOf(d.getType()), player);

                    System.out.println("Bought: " + towerToPlace.getName());
                    break;
                }
                
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && towerToPlace != null && game.getMap().getSelectedTile() != null) {
                    placeTower();
                } else if (towerToPlace != null) {
                    drawGhostTower();
                }
            }
        }
    }
    
    private void placeTower() {
        Tile t = game.getMap().getSelectedTile();
        Globals.client.send(new BuyTowerPacket(t.getX(), t.getY(), towerToPlace.getType().name()));
        System.out.println("Send packet");
        towerToPlace = null;
    }
    
    private void drawGhostTower() {
        Tile t = game.getMap().getSelectedTile();
        if (t != null) {
            TextureGlobals.SPRITE_BATCH.draw(
                    TextureFactory.getTexture(towerToPlace.getSprite()),
                    t.getX(true),
                    t.getY(true),
                    Globals.TILE_WIDTH, Globals.TILE_HEIGHT
            );
        }
    }
    
   
}
