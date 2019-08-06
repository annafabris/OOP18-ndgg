package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * An interface for the {@link BodyPropertiesWorldImpl} class.
 */
public interface BodyPropertiesWorld {

    /**
     * Updates the state of the {@link BodyPropertiesWorld}.
     */
    void update();

    /**
     * Returns the {@link World} associated with this {@link BodyPropertiesWorld}.
     * @return {@link World}.
     */
    World getWorld();

    /**
     * Puts an association inside this PhysicalWorld between a {@link Body}, its {@link BodyProperties} and its {@link EntityType}.
     * @param bodyProperties {@link BodyProperties}
     * @param body {@link Body}
     * @param type {@link EntityType}
     */
    void putPhysicalBodyToBody(BodyProperties bodyProperties, Body body, EntityType type);

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
     * Returns the {@link Door} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link Door}
     */
    Door getDoorFromBody(Body body);

    /**
     * Returns the {@link Platform} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link Platform}
     */
    Platform getPlatformFromBody(Body body);

    /**
     * Returns the right {@link EntityType} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link EntityType}
     */
    EntityType getEntityTypeFromBody(Body body);

    /**
     * Returns the {@link BodyProperties} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link BodyProperties}
     */
    BodyProperties getBodyPropertiesFromBody(Body body);
}
