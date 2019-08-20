package it.unibo.ndgg.model.physic.body;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * A class extending {@AbstractPhysicalBody} that is also extended by all the Dynamic Bodies if needed (Player and Sword).
 */
public class DynamicBodyProperties extends AbstractBodyProperties {

    private final Body body;
    private EntityState currentState;

    /**
     * Builds a physical dynamic body.
     * @param body 
     *          a physical body
     */
    public DynamicBodyProperties(final Body body) {
        super(body);
        this.body = body;
        this.currentState = EntityState.STAYING_STILL;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public EntityState getState() {
        return this.currentState;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Pair<Double, Double> getVelocity() {
        return Pair.of(this.body.getLinearVelocity().x, this.body.getLinearVelocity().y);
    }

    /**
     * Applies the movement to the {@link org.dyn4j.dynamics.Body}.
     * @param entityMovement the {@link it.unibo.ndgg.model.entity.EntityState} necessary to change the current state
     * @param x the horizontal component of the movement to apply
     * @param y the vertical component of the movement to apply
     */
    public void applyImpulse(final EntityMovement entityMovement, final double x, final double y) {
        this.currentState = entityMovement.getAssociatedEntityState();
        this.body.applyImpulse(new Vector2(x, y));
    }
    
    /**
     * Translate the {@link org.dyn4j.dynamics.Body}.
     * @param entityMovement the {@link it.unibo.ndgg.model.entity.EntityState} necessary to change the current state
     * @param x the horizontal component of the movement to apply
     * @param y the vertical component of the movement to apply
     */
    public void translate(final EntityMovement entityMovement, final double x, final double y) {
        this.currentState = entityMovement.getAssociatedEntityState();
        this.body.translate(new Vector2(x, y));
    }


    /**
     * Set the {@link org.dyn4j.dynamics.Body} velocity.
     * @param entityMovement the {@link it.unibo.ndgg.model.entity.EntityState} necessary to change the current state
     * @param x the horizontal velocity component
     * @param y the vertical velocity component
     */
    public void setVelocity(final EntityMovement entityMovement, final double x, final double y) {
        this.currentState = entityMovement.getAssociatedEntityState();
        this.body.setLinearVelocity(new Vector2(x, y));
    }

    /**
     * Method used to change the {@link Sword} {@link it.unibo.ndgg.model.entity.EntityMovement} without using the applyMovement method.
     * @param state {@link it.unibo.ndgg.model.entity.EntityMovement}
     */
    public void changeCurrentState(final EntityState state) {
        this.currentState = state;
    }
}
