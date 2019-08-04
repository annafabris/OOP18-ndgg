package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

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
     * @param direction
     *          the first direction of this entity
     * @return the entity Player
     */
    Player createPlayer(Double height, Double width, Pair<Double, Double> position, EntityDirection direction);

    /**
     * It builds a new Sword with its properties.
     * @param height
     *          the height of the body
     * @param width
     *          the width of the body
     * @param position
     *          the position of the body in the {@link World}
     * @param player
     *          the player that have this sword equipped
     * @param direction
     *          the first direction of this entity
     * @return the entity Sword
     */
    Sword createSword(Double height, Double width, Pair<Double, Double> position, Player player, EntityDirection direction);
}
