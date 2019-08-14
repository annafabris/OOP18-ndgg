package it.unibo.ndgg.controller;

/**
 * Interface modeling the controller for the main menu.
 */
public interface MenuController {

    /**
     * Load main menu screen.
     */
    void mainMenu();

    /**
     * Start a new game.
     * @throws Exception 
     */
    void newGame() throws Exception;

    /**
     * Load options screen.
     */
    void options();

    /**
     * Exits from the application.
     */
    void exit();

}
