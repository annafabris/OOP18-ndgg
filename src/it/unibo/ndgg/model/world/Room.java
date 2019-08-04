package it.unibo.ndgg.model.world;

import java.util.List;
import java.util.Map;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityType;

/**
 * A class that represents a {@link Room} in which the game takes place until one of the {@link Player} wins.
 */
public interface Room {

    /**
     * Updates all the {@link Entity} in the {@link Room}.
     */
    void update();

    /**
     * Add all the {@link Entity} to the {@link Room}.
     * @param entities a Map containing the {@link EntityType} associated to the List of all the {@link AbstractEntity} of that type
     */
    void setEntities(final Map<EntityType, List<AbstractEntity>> entities);

    Map<EntityType, List<AbstractEntity>> getEntities();
}
