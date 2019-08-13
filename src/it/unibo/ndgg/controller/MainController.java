package it.unibo.ndgg.controller;

/**
 * Interface modeling the controller for the application.
 */
public interface MainController {

    /**
     * Switches screen to main menu and start a new {@link MenuController}. 
     */
    void menu();

    /**
     * Start a new {@link GameController}.
     */
    void startNewGame();

    /**
     * Exits the application.
     */
    void quit();

}
