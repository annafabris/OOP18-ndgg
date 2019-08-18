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
 * Represent the World in which the physic of the game takes place.
 */
public interface BodyPropertiesWorld {

    /**
     * Updates the state of the {@link BodyPropertiesWorld}.
     */
    void update();

    /**
     * Returns the {@link org.dyn4j.dynamics.World} associated with this {@link BodyPropertiesWorld}.
     * @return {@link org.dyn4j.dynamics.World}.
     */
    World getWorld();

    /**
     * Puts an association inside this PhysicalWorld between a {@link org.dyn4j.dynamics.Body}, its 
     * {@link it.unibo.ndgg.model.physic.body.BodyProperties} and its {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param bodyProperties {@link it.unibo.ndgg.model.physic.body.BodyProperties}
     * @param body {@link org.dyn4j.dynamics.Body}
     * @param type {@link it.unibo.ndgg.model.entity.EntityType}
     */
    void putPhysicalBodyToBody(BodyProperties bodyProperties, Body body, EntityType type);

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Player} associated with the correct {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     */
    Player getPlayerFromBody(Body body);

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword} associated with the correct {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
     */
    Sword getSwordFromBody(Body body);

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitystatic.Door} given its {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.entity.entitystatic.Door}
     */
    Door getDoorFromBody(Body body);

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitystatic.Platform} given its {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.entity.entitystatic.Platform}
     */
    Platform getPlatformFromBody(Body body);

    /**
     * Returns the right {@link it.unibo.ndgg.model.entity.EntityType} given its {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.entity.EntityType}
     */
    EntityType getEntityTypeFromBody(Body body);

    /**
     * Returns the {@link it.unibo.ndgg.model.physic.body.BodyProperties} given its {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.physic.body.BodyProperties}
     */
    BodyProperties getBodyPropertiesFromBody(Body body);
}
