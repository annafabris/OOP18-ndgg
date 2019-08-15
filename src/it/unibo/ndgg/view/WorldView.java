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
    
    void PlayerWon(final int PlayerID);
}
