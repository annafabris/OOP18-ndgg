package it.unibo.ndgg.model.entity.entitydynamic;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents the implementation of {@link DynamicEntity}.
 */
public abstract class AbstractDynamicEntity extends AbstractEntity implements DynamicEntity {

    private EntityDirection direction;

    /**
     * Builds a new dynamicEntity.
     * @param direction
     *          it the current {@link EntityDirection} of the {@link DynamicEntity}
     * @param body
     *          it is the body represented by this entity
     */
    public AbstractDynamicEntity(final EntityDirection direction, final BodyProperties body) {
        super(body);
        this.direction = direction;
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
    public abstract void move(EntityMovement movement);

}
