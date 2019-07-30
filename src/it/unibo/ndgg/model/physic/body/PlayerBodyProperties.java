package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.physic.movement.PlayerMovementVectorValues;

/**
 * The {@link Player} expansion of {@link DynamicBodyProperties}.
 */
public class PlayerBodyProperties extends DynamicBodyProperties {

    public PlayerBodyProperties(Body body) {
        super(body);
    }

    public void applyMovement(final EntityMovement movement, final double x, final double y) {
        PlayerMovementVectorValues playerMovement = new PlayerMovementVectorValues(movement);
        this.currentState = playerMovement.getState();
        this.body.applyImpulse(new Vector2(x, y));
    }

    public void setVelocity(final EntityMovement movement, final double x, final double y) {
        PlayerMovementVectorValues playerMovement = new PlayerMovementVectorValues(movement);
        this.currentState = playerMovement.getState();
        this.body.setLinearVelocity(new Vector2(x, y));
    }
}
