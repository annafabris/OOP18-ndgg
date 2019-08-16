package it.unibo.ndgg.controller;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

/**
 * The controller of the Game.
 */
public interface GameController {

    /**
     * Starts the game.
     * @throws Exception
     */
    void game() throws Exception;

    /**
     * Calls the update methods in the View and model unless the game ended.
     */
    void updateModelAndView();

    /**
     * Exit the game when one of the players won.
     */
    void exit();

    /**
     * Returns the requested {@link it.unibo.ndgg.model.entity.entitydynamic.Player}.
     * @param playerID the Id of the players (0 or 1)
     * @return {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     */
    Player getPlayer(int playerID);

    /**
     * Returns the requested {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     * @param swordId the Id of the players (0 or 1)
     * @return {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
     */
    Sword getSword(int swordId);
}
