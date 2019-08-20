package it.unibo.ndgg.model;

/**
 * An enum that represent the state of the game. 
 */
public enum GameState {

    /**
     * The game is still being played.
     */
    IS_GOING, 

    /**
     * The "left" player won the game (PlayerId = 0).
     */
    PLAYERL_WON,

    /**
     * The "right" player won the game (PlayerId = 1).
     */
    PLAYERR_WON;
}
