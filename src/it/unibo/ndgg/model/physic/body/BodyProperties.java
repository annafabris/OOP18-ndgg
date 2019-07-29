package it.unibo.ndgg.model.physic.body;

import org.apache.commons.lang3.tuple.Pair;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents a physical body that represents a {@link it.unibo.ndgg.model.entity.Entity}.
 */
public interface BodyProperties { 
    /**
     * Return the positions of the {@link it.unibo.ndgg.model.entity.Entity}.
     * @return the positions of the {@link it.unibo.ndgg.model.entity.Entity}, as a {@link Pair} that represents 
     * the x and the y coordinates 
     */
    Pair<Double, Double> getPosition();

    /** Return the state {@link it.unibo.ndgg.model.entity.EntityState} of the entity.
     * {@link it.unibo.ndgg.model.entity.Entity}
     * @return the state of the {@link it.unibo.ndgg.model.entity.Entity}
     */
    EntityState getState();

    /**
     * Return a {@link Pair} that represent the x and y components of the speed of the {@link it.unibo.ndgg.model.entity.Entity}.
     * @return the x and y components of speed like a {@link Pair}
     */
    Pair<Double, Double> getVelocity();

    /**
     * Return a {@link Pair} that represent height and width of the {@link it.unibo.ndgg.model.entity.Entity}.
     * @return the height and width of the {@link} like a {@link Pair}
     */
    Pair<Double, Double> getDimension();
}
