package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 *
 */
public abstract class AbstractEntity implements Entity {
    private final BodyProperties body;

    /**
     * It is a constructor for this class that require a {@link it.unibo.ndgg.model.physic.body.BodyProperties}.
     * @param body 
     *          an element of {it.unibo.ndgg.model.physic.body.BodyProperties}
     */
    public AbstractEntity(final BodyProperties body) {
        super();
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.body.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return this.body.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityType getType();

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getDimension() {
        return this.body.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getVelocity() {
        return this.body.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyProperties getBody() {
        return this.body;
    }
}
