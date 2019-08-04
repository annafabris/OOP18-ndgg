package it.unibo.ndgg.model.entity.entitydynamic;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;

/**
 * Represents a dynamic entity with its direction and its movements.
 */
public interface DynamicEntity {

    /**
     * Returns the {@link Entity} current direction.
     * @return
     *        the {@link EntityDirection} of this entity
     */
    EntityDirection getCurrentDirection();

    /**
     * Set the {@link EntityDirection} of this entity.
     * @param direction
     *          it is the new direction of the dynamic entity
     */
    void setCurrentDirection(EntityDirection direction);

    /**
     * Represents the change of dynamic entity's state, for an input or a condition.
     * @param movement
     *          it is the {@link EntityMovement} that the player have to do
     */
    void move(EntityMovement movement);

}
