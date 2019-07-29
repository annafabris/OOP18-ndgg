package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityMovement;

/**
 * {@inheritDoc}.
 */
public class SwordBodyProperties extends DynamicBodyProperties {

    public SwordBodyProperties(Body body) {
        super(body);
    }

    public void applyMovement(final EntityMovement movement, final double x, final double y) {
    }

    public void setVelocity(final EntityMovement movement, final double x, final double y) {
    }
}
