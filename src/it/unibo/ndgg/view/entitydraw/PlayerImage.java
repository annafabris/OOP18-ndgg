package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents an association between a {@link PlayerFrames} and a {@link it.unibo.ndgg.model.entity.EntityState} 
 * and {@link it.unibo.ndgg.model.entity.EntityDirection}. 
 * Each playerFrames represents a state with a particular direction.
 */
public interface PlayerImage {

    /**
     * Returns the player frame associates an EntityState and a Entitydirection.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the player frame associates an EntityState and a EntityDirection
     */
    PlayerFrames getPlayerFrames(EntityState state, EntityDirection direction);

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
     * Return the player one sheet path.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the sprite sheet path
     */
    String getPlayer1Path(EntityState state, EntityDirection direction);

    /**
     * Return the player two sheet path.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the sprite sheet path
     */
    String getPlayer2Path(EntityState state, EntityDirection direction);

}
