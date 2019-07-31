package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents the entity's type creation. It uses a {@link Entity}
 */
public interface EntityFactory {

    /**
     * It builds a new Player with its properties.
     * @param height
     *          the height of the body
     * @param width
     *          the width of the body
     * @param position
     *          the position of the body in the {@link World}
     * @return the entity Player
     */
    Entity createPlayer(Double height, Double width, Pair<Double, Double> position);

    /**
     * It builds a new Sword with its properties.
     * @param height
     *          the height of the body
     * @param width
     *          the width of the body
     * @param position
     *          the position of the body in the {@link World}
     * @return the entity Sword
     */
    Entity createSword(Double height, Double width, Pair<Double, Double> position);
}
