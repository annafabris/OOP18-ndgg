package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents a physical object (entity) with an extension, a position, a mass and a type which 
 * depends on {@link EntityType} and a state that depends on {@link EntityState}, 
 * inside the world {@link it.unibo.ndgg.model.world.World}.
 */
public interface Entity {
    /** 
     * Return a pair that represent the position of {@link Entity}.
     * @return the position of the entity as a {@link Pair} with the first number representing the
     * x coordinate and the second number the y coordinate in {@link it.unibo.ndgg.model.world.World}
     */
    Pair<Double, Double> getPosition();

    /** 
     * Return the state {@link EntityState} of the entity {@link Entity}.
     * @return the state {@link Entity} is in
     */
    EntityState getState();

    /**
     * Return the type {@link EntityType} of the entity {@link Entity}.
     * @return the type {@link Entity} is
     */
    EntityType getType();

    /**
     * Return a {@link Pair} that represent the component x and y of velocity of {@link Entity}. 
     * @return the components x and y of velocity as a {@link Pair} of {@link Entity}
     */
    Pair<Double, Double> getVelocity();

    /**
     * Return a {@link Pair} that represent height and width of {@link Entity} . 
     * @return the height and width as a {@link Pair} of {@link Entity}
     */
    Pair<Double, Double> getDimension();

    /**
     * Return a body of {@link Entity}. 
     * @return body of {@link BodyProperties}
     */
    BodyProperties getBody();
}
