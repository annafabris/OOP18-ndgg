package it.unibo.ndgg.model.physic.movement;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * {@inheritDoc}.
 */
public class MovementVectorValuesImpl implements MovementVectorValues {

    private static final Map<EntityMovement, Pair<EntityState, Vector2>> VALUES = new HashMap<>();

    static {
        //TODO N.B. cambiare e testare questi valori e quali movimenti ci vanno
        VALUES.put(EntityMovement.JUMP, Pair.of(EntityState.JUMPING, new Vector2(0, 11)));
        VALUES.put(EntityMovement.MOVE_RIGHT, Pair.of(EntityState.MOVING_RIGHT, new Vector2(1, 0)));
        VALUES.put(EntityMovement.MOVE_LEFT, Pair.of(EntityState.MOVING_LEFT, new Vector2(-1, 0)));
        VALUES.put(EntityMovement.DROP, Pair.of(EntityState.DROPPING, new Vector2(0, -5)));
        VALUES.put(EntityMovement.EQUIP, Pair.of(EntityState.EQUIPPED, new Vector2(0, 5)));    //TODO vedere come implentarlo
        VALUES.put(EntityMovement.STAY_STILL, Pair.of(EntityState.STAYING_STILL, new Vector2(0, 0))); //TODO è necessario?
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 getMovementVector(final EntityMovement currentMovement) {
        return MovementVectorValuesImpl.VALUES.get(currentMovement).getValue();
    }

    /**
     * {@inheritDoc}
     */
    public EntityState getState(final EntityMovement currentMovement) {
        return MovementVectorValuesImpl.VALUES.get(currentMovement).getKey();
    }
}
