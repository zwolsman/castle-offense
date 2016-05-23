package com.s31b.castleoffense;

import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.data.EntityDAO;
import com.s31b.castleoffense.data.MongoDB;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates the entities used in the game
 *
 * @author GoosLaptop
 */
public class EntityFactory {

    private static final MongoDB CONS_DB = new MongoDB();
    private static final List<? extends EntityDAO> CONS_ENTITIES = getAllEntities();

    private static List<EntityDAO> getAllEntities() {
        List<EntityDAO> retval = new ArrayList();
        retval.addAll(CONS_DB.getAll(DefensiveDAO.class));
        retval.addAll(CONS_DB.getAll(OffensiveDAO.class));
        return retval;
    }

    public static void init() {
        //At this point, the connection to the database has been established and all the entities have been loaded.
    }

    /**
     * get the price of given entity type
     *
     * @param type type to get price of
     * @return price of given type
     */
    public static int getEntityPriceByType(EntityType type) {
        for (EntityDAO e : CONS_ENTITIES) {
            if (e.getType().equals(type.name())) {
                if (e.getClass() == DefensiveDAO.class){
                    return ((DefensiveDAO) e).getPrice();
                } else if (e.getClass() == OffensiveDAO.class) {
                    return ((OffensiveDAO) e).getPrice();
                }
            }
        }
        return -1;
    }

    /**
     * Buy an entity
     *
     * @param type type of entity
     * @param player player that wants to buy entity
     * @return newly bought Entity object
     */
    public static Entity buyEntity(EntityType type, Player player) {
        for (EntityDAO e : CONS_ENTITIES) {
            if (e.getType().equals(type.name())) {
                if (e.getClass() == DefensiveDAO.class) {
                    return new Defensive((DefensiveDAO) e, player);
                } else if (e.getClass() == OffensiveDAO.class) {
                    return new Offensive((OffensiveDAO) e, player);
                }
            }
        }
        return null;
    }
}
