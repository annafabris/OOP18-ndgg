package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.physic.movement.SwordMovementVectorValues;

/**
 * {@inheritDoc}.
 */
public class SwordBodyProperties extends DynamicBodyProperties {

    public SwordBodyProperties(Body body) {
        super(body);
    }

    public void applyMovement(final EntityMovement movement, final double x, final double y) {
        SwordMovementVectorValues sword = new SwordMovementVectorValues(movement);
        this.currentState = sword.getState();
        this.body.applyImpulse(new Vector2(x, y));
    }

    public void setVelocity(final EntityMovement movement, final double x, final double y) {
        SwordMovementVectorValues sword = new SwordMovementVectorValues(movement);
        this.currentState = sword.getState();
        this.body.setLinearVelocity(new Vector2(x, y));
    }
}
