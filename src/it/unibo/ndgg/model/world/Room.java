package it.unibo.ndgg.model.world;

import it.unibo.ndgg.model.entity.Entity;

/**
 * A class that represents a {@link Room} in which the game takes place until one of the Player wins.
 */
//TODO add Player link dopo la creazione della classe Player
public interface Room {

    /**
     * Updates all the {@link Entity} in the {@link Room}.
     */
    void update();

    /**
     * Add one {@link Entity} to the {@link Room}.
     * @param entity the {@link Entity} to add to the {@link Room}
     */
    void addEntity(Entity entity);
}
