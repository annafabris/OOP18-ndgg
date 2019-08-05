package it.unibo.ndgg.model.physic.body;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents all possible type of {@link BodyProperties}.
 */
public abstract class AbstractBodyProperties implements BodyProperties {

    private final Body body;

    /**
     * It is a Constructor for this class that require a {@link org.dyn4j.dynamics.Body}.
     * @param body 
     *          an element of {@link org.dyn4j.dynamics.Body} that represents the physical 
     *          part of entity
     */
    public AbstractBodyProperties(final Body body) {
        super();
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return Pair.of(this.body.getWorldCenter().x, this.body.getWorldCenter().y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityState getState();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists() {
        return this.body.isActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getDimension() {
        final Convex rectangle = this.body.getFixture(0).getShape();
        final double width = ((Rectangle) rectangle).getWidth();
        final double height = ((Rectangle) rectangle).getHeight();

        return Pair.of(height, width);

    }
}
