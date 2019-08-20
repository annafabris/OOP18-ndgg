package it.unibo.ndgg.view;

import java.util.Set;

import it.unibo.ndgg.controller.GameControllerImpl;
import javafx.scene.input.KeyEvent;

/**
 * A class that takes care of the view of the game.
 */
public interface WorldView {

    /**
     * Starts the view of the game.
     * @param gameControllerImpl {@link GameControllerImpl}
     */
    void startGame(GameControllerImpl gameControllerImpl);

    /**
     * Updates the view of the game.
     */
    void update();

    /**
     * Gets called when one of the players won and it display the final view.
     * @param playerID the Id of the winner
     */
    void playerWon(int playerID);

    /**
     * 
     * @return a collection of input
     */
    Set<KeyEvent> getInputs();
}
