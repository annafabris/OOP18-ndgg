package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * {@inheritDoc}.
 */
public class SwordBodyProperties extends DynamicBodyProperties {

    public SwordBodyProperties(Body body) {
        super(body);
    }

    public void applyMovement(final EntityMovement movement, final double x, final double y) {
        MovementVectorValuesImpl sword = new MovementVectorValuesImpl();
        this.currentState = sword.getState(movement);
        this.body.applyImpulse(new Vector2(x, y));
    }

    public void setVelocity(final EntityMovement movement, final double x, final double y) {
        MovementVectorValuesImpl sword = new MovementVectorValuesImpl();
        this.currentState = sword.getState(movement);
        this.body.setLinearVelocity(new Vector2(x, y));
    }
    
    /**
     * Method used to change the {@link Sword} {@link EntityState} without using the applyMovement method
     * @param state {@link EntityState}
     */
    public void changeCurrentState(final EntityState state) {
        this.currentState = state;
    }
}
