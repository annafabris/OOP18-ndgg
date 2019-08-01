package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

/**
 *
 */
public interface BodyPropertiesWorld {

    /**
     * Returns the {@link Player} associated with the correct {@link Player}.
     * @param body {@link Body}
     * @return {@link Sword}
     */
    Player getPlayerFromBody(Body body);

    /**
     * Returns the {@link Sword} associated with the correct {@link Body}.
     * @param body {@link Body}
     * @return {@link Sword}
     */
    Sword getSwordFromBody(Body body);

    /**
     * Returns the right {@link EntityType} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link EntityType}
     */
    EntityType getEntityTypeFromBody(Body body);

}
