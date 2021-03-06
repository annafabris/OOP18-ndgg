package it.unibo.ndgg.model.physic.movement;

import java.util.HashMap;
import java.util.Map;

import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;

/**
 * {@inheritDoc}.
 */
public class MovementVectorValuesImpl implements MovementVectorValues {

    private static final Double THROW_X_MOVEMENT = 20.0;
    private static final Double MOVE_X_MOVEMENT = 10.0;
    private static final Double JUMP_Y_MOVEMENT = 100.0;
    private static final Double DROP_Y_MOVEMENT = 10.0;
    private static final Double DIE_X_MOVEMENT = 1.0;
    private static final Double DIE_Y_MOVEMENT = 3.0;
    private static final Map<EntityMovement, Vector2> VALUES = new HashMap<>();

    static {
        VALUES.put(EntityMovement.DIE_LEFT, new Vector2(DIE_X_MOVEMENT, -DIE_Y_MOVEMENT));
        VALUES.put(EntityMovement.DIE_RIGHT, new Vector2(DIE_X_MOVEMENT, -DIE_Y_MOVEMENT));
        VALUES.put(EntityMovement.DROP_LEFT, new Vector2(0, DROP_Y_MOVEMENT));
        VALUES.put(EntityMovement.DROP_RIGHT, new Vector2(0, DROP_Y_MOVEMENT));
        VALUES.put(EntityMovement.JUMP_UP_LEFT, new Vector2(0, JUMP_Y_MOVEMENT));
        VALUES.put(EntityMovement.JUMP_UP_RIGHT, new Vector2(0, JUMP_Y_MOVEMENT));
        VALUES.put(EntityMovement.MOVE_LEFT, new Vector2(-MOVE_X_MOVEMENT, 0));
        VALUES.put(EntityMovement.MOVE_RIGHT, new Vector2(MOVE_X_MOVEMENT, 0));
        VALUES.put(EntityMovement.THROW_LEFT, new Vector2(-THROW_X_MOVEMENT, 0));
        VALUES.put(EntityMovement.THROW_RIGHT, new Vector2(THROW_X_MOVEMENT, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 getMovementVector(final EntityMovement currentMovement) {
        return MovementVectorValuesImpl.VALUES.get(currentMovement);
    }
}
