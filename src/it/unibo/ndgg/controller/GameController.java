package it.unibo.ndgg.controller;

import java.util.List;
import java.util.Map;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;

/**
 * The controller of the Game.
 */
public interface GameController {

    /**
     * Starts the game.
     * @throws Exception
     */
    void newGame();

    /**
     * Calls the update methods in the View and model unless the game ended.
     */
    void updateModelAndView();

    /**
     * Exit the game when one of the players won.
     */
    void exit();

    /**
     * Returns all entities in the {@link World}.
     * @return
     *       the entities with its types.
     */
    Map<EntityType, List<AbstractEntity>> getEntities();
}
