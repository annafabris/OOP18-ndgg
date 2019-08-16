package it.unibo.ndgg.view;

import it.unibo.ndgg.controller.GameControllerImpl;

/**
 * A class that takes care of the view of the game.
 */
public interface WorldView {

    /**
     * starts the view of the game.
     */
    void startGame(GameControllerImpl gameControllerImpl) throws Exception;

    /**
     * updates the view of the game.
     */
    void update();

    /**
     * gets called when one of the players won and it display the final view.
     */
    void playerWon(final int PlayerID);
}
