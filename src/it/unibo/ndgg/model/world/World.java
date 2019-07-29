package it.unibo.ndgg.model.world;

import it.unibo.ndgg.model.entity.Entity;

/**
 * A class that represents a list of {@link Room} in which the game takes place.
 */
public interface World {

    /**
     * Initialize all the {@link Entity} and ....
     */
    void start();

    /**
     * Updates all the {@link Entity} in the {@link World}.
     */
    void update();
}
