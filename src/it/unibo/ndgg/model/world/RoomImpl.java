package it.unibo.ndgg.model.world;

import java.util.List;
import java.util.Map;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;

/**
 * {@inheritDoc}.
 */
public class RoomImpl implements Room {

    private Map<EntityType, List<AbstractEntity>> entities;
    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setEntities(final Map<EntityType, List<AbstractEntity>> entities) {
        this.entities = entities;
    }

    public Map<EntityType, List<AbstractEntity>> getEntities() {
        return entities;
    }
}
