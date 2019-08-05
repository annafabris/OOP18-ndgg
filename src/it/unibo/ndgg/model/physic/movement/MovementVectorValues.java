package it.unibo.ndgg.model.physic.movement;

import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;

/**
 * An interface implemented by {@link MovementVectorValuesImpl}. 
 * It creates an association between an {@link EntityMovement} and the vector of the movement {@link Vector2}
 */
public interface MovementVectorValues {

    /**
     * Returns the value the impulse vector should have on the x and y axes.
     * @param currentMovement the {@link EntityMovement} to find the associated {@link Vector2}
     * @return impulse as a {@link Vector2}
     */
    Vector2 getMovementVector(EntityMovement currentMovement);
}
