package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;

/**
 * Represents the implementation of {@link DynamicEntity}.
 */
public abstract class AbstractDynamicEntity extends AbstractEntity implements DynamicEntity {

    private Optional<DynamicBodyProperties> body;
    private EntityDirection direction;

    /**
     * Builds a new {@link DynamicEntity}.
     * @param direction
     *          it the current {@link EntityDirection} of the {@link DynamicEntity}
     * @param body
     *          it is the body represented by this entity
     */
    public AbstractDynamicEntity(final EntityDirection direction, final Optional<DynamicBodyProperties> body) {
        super();
        if (body.isPresent()) {
            super.setBody(Optional.of(body.get()));
            this.body = body;
        } else {
            this.body = Optional.empty();
            super.setBody(Optional.empty());
        }
        this.direction = direction;
    }

    /**
     * Sets the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} again.
     * @param body an Optional of the {it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     */
    protected void setDynamicBody(final Optional<DynamicBodyProperties> body) {
        if (body.isPresent()) {
            super.setBody(Optional.ofNullable(body.get()));
            this.body = body;
        } else {
            this.body = Optional.empty();
            super.setBody(Optional.empty());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Pair<Double, Double> getVelocity() {
        if (this.body.isPresent()) {
            return this.body.get().getVelocity();
        } else {
            throw new IllegalStateException("The Body is not present");
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityType getType();

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        if (body.isPresent()) {
            return this.body.get().getState();
        } else {
            //TODO attenzione potrebbero esserci problemi
            return EntityState.EQUIPPED;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeEntityState(final EntityState state) {
        if (body.isPresent()) {
            this.body.get().changeCurrentState(state);
        } else {
            throw new IllegalStateException("The Body is not present");
        }
    }

}
