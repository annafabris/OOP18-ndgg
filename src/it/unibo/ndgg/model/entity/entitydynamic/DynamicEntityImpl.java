package it.unibo.ndgg.model.entity.entitydynamic;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * Represents the implementation of {@link DynamicEntity}.
 */
public abstract class DynamicEntityImpl extends AbstractEntity implements DynamicEntity {

    private final DynamicBodyProperties body;
    private EntityDirection direction;

    /**
     * Builds a new dynamicEntity.
     * @param direction
     *          it the current {@link EntityDirection} of the {@link DynamicEntity}
     * @param body
     *          it is the body represented by this entity
     */
    public DynamicEntityImpl(final EntityDirection direction, final DynamicBodyProperties body) {
        super(body);
        this.body = body;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public Pair<Double, Double> getVelocity() {
        return this.body.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityDirection getCurrentDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentDirection(final EntityDirection direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final EntityMovement movement) {
        final MovementVectorValues movementValue = new MovementVectorValuesImpl();
        this.body.applyMovement(movement, movementValue.getMovementVector(movement).x, 
                                movementValue.getMovementVector(movement).y);
    }

}
