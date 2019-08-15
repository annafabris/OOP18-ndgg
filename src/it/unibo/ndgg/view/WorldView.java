package it.unibo.ndgg.view;

/**
 * A class that takes care of the view of the game.
 */
public interface WorldView {

    /**
     * starts the view of the game.
     */
    void startGame() throws Exception;

    /**
     * updates the view of the game.
     */
    void update();
    
    /**
     * gets called when one of the Players won and it display the final view
     */
    void PlayerWon(final int PlayerID);
}
