package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityState;

/**
 * A class extending {@AbstractPhysicalBody} that is also extended by all the Dynamic Bodies if needed (Player and Sword).
 */
public class DynamicBodyProperties extends AbstractBodyProperties {

    protected final Body body;
    protected EntityState currentState;

    DynamicBodyProperties(final Body body) {
        super(body);
        this.body = body;
        this.currentState = EntityState.STAYING_STILL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return this.currentState;
    }
}
