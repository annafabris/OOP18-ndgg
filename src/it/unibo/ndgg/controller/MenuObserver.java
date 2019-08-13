package it.unibo.ndgg.controller;

/**
 * Observer for {@link MainMenu}.
 */
public interface MenuObserver {

    /**
     * Load main menu screen.
     */
    void mainMenu();

    /**
     * Start a new game.
     */
    void newGame();

    /**
     * Load options screen.
     */
    void options();

    /**
     * Exits from the application.
     */
    void exit();

}
