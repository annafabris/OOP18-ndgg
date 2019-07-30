package it.unibo.ndgg.model.physic.movement;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;

/**
 *
 */
public class PlayerMovementVectorValues implements MovementVectorValues {

    private static Map<EntityMovement, Pair<EntityState, Vector2>> values = new HashMap<>();
    private final EntityMovement currentPlayerMovementType;

    public PlayerMovementVectorValues(final EntityMovement currentPlayerMovementType) {
        super();
        this.currentPlayerMovementType = currentPlayerMovementType;
    }

    /**
     * Returns the current {@link EntityMovement} associated to this value.
     * @return the current {@link EntityMovement}
     */
    public EntityMovement getMovementType() {
        return this.currentPlayerMovementType;
    }

    /**
     * Return the current state of the Entity.
     * @return entityState the {@link EntityState}
     */
    public EntityState getState() {
        return values.get(this.currentPlayerMovementType).getLeft();
    }

}
