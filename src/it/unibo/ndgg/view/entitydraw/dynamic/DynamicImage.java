package it.unibo.ndgg.view.entitydraw.dynamic;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents the dynamic image of the {@link Sword} or of {@link Player}.
 */
public interface DynamicImage {

    /**
     * Returns the number of frames in a specific sprite sheet.
     * @param state 
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the number of frames in the sprite sheet
     */
    int getNumberOfFrames(EntityState state, EntityDirection direction);

    /**
     * Represents all possible dynamic's state.
     * @return
     *        a list of a pair with a state and a direction, that represents all possible player's state
     */
    List<Pair<EntityState, EntityDirection>> getAllPossibleStates();
}
