package it.unibo.ndgg.view;

import java.util.List;

import it.unibo.ndgg.controller.GameController;
import it.unibo.ndgg.controller.Input;

/**
 * A class that takes care of the view of the game.
 */
public interface WorldView {

    /**
     * Starts the view of the game.
     * @param controller the related game controller.
     */
    void startGame(GameController controller);

    /**
     * Updates the view of the game.
     */
    void update();

    /**
     * Gets called when one of the players won and it display the final view.
     * @param msg the message to show.
     */
    void playerWon(String msg);

    /**
     * @return a collection of input
     */
    List<Input> getInputs();

    /**
     * Changes the Background to the following one.
     */
    void changeRoom();
}
