package it.unibo.ndgg.model.entity;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents a physical object (entity) with an extension, a position, a mass and a type which 
 * depends on {@link EntityType} and a state that depends on {@link EntityState}, 
 * inside the world {@link it.unibo.ndgg.model.world.World}.
 */
public interface Entity {

    /** 
     * Returns a pair that represent the position of {@link Entity}.
     * @return the position of the entity as a {@link Pair} with the first number representing the
     * x coordinate and the second number the y coordinate in {@link it.unibo.ndgg.model.world.World}
     */
    Pair<Double, Double> getPosition();

    /** 
     * Returns the state {@link EntityState} of the entity {@link Entity}.
     * @return the state {@link Entity} is in
     */
    EntityState getState();

    /**
     * Returns the type {@link EntityType} of the entity {@link Entity}.
     * @return the type {@link Entity} is
     */
    EntityType getType();

    /**
     * Returns if {@link it.unibo.oop18.nidhogg.model.entity.Entity} is alive or not.
     * @return if {@link it.unibo.oop18.nidhogg.model.entity.Entity} is alive or not 
     */
    boolean isAlive();

    /**
     * Returns a {@link Pair} that represent height and width of {@link Entity} . 
     * @return the height and width as a {@link Pair} of {@link Entity}
     */
    Pair<Double, Double> getDimension();

    /**
     * Returns a body of {@link Entity}. 
     * @return body of {@link BodyProperties}
     */
    BodyProperties getBody();

    /**
     * Change the body of the {@link Entity}. It is used only in this moment to operate with Sword.
     * @param body
     *          the new body of the {@link Entity}, or an empty optional if the entity, in a moment not 
     *          have a body
     */
    void setBody(Optional<BodyProperties> body);
}
