package it.unibo.ndgg.model.entity.entitydynamic;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * Represents the implementation of {@link DynamicEntity}.
 */
public abstract class AbstractDynamicEntity extends AbstractEntity implements DynamicEntity {

    private final DynamicBodyProperties body;
    private EntityDirection direction;

    /**
     * Builds a new {@link DynamicEntity}.
     * @param direction
     *          it the current {@link EntityDirection} of the {@link DynamicEntity}
     * @param body
     *          it is the body represented by this entity
     */
    public AbstractDynamicEntity(final EntityDirection direction, final DynamicBodyProperties body) {
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
    public abstract void move(final EntityMovement movement);

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeEntityState(final EntityState state) {
       this.body.changeCurrentState(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityType getType();
}
