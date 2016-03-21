package com.s31b.castleoffense;

import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import java.awt.image.BufferedImage;

/**
 *
 * @author GoosLaptop
 */
public class EntityFactory {
    
    /**
     * get the price of given entity type
     * @param type type to get price of
     * @return price of given type
     */
    public static float getEntityPriceByType(EntityType type) {
        switch(type) {
            case Defensive_Tower1:
                return Globals.PRICE_DEFENSIVE * EntityType.Defensive_Tower1.getMultiplyFactor();
            case Offensive_Npc1:
                return Globals.PRICE_OFFENSIVE * EntityType.Offensive_Npc1.getMultiplyFactor();
            default:
                return 0.0f;
        }
    }
    
    /**
     * Buy an entity
     * @param type type of entity
     * @param player player that wants to buy entity
     * @param tile tile that entity will spawn on
     * @return newly bought Entity object
     */
    public static Entity buyEntity(EntityType type, Player player, Tile tile) {
        switch(type) {
            case Defensive_Tower1:
                return new Defensive(type, "Tower 1", "Basic lvl 1 tower", new BufferedImage(1,1,1), 
                    player, getEntityPriceByType(type), Globals.DAMAGE_PER_SECOND, Globals.DEFENSIVE_RANGE, tile);
            case Offensive_Npc1:
                return new Offensive(type, "Monster 1", "Basic lvl 1 monster", new BufferedImage(1,1,1), 
                    player, getEntityPriceByType(type), Globals.OFFENSIVE_HITPOINTS, Globals.MOVEMENT_SPEED, 
                        Globals.KILL_REWARD, tile);
            default:
                // if this exception is thrown not all EntityType's are included in the switch
                throw new AssertionError(type.name()); 
        }
    }
}
