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
     * @throws Exception 
     */
    void startNewGame() throws Exception;

    /**
     * Exits the application.
     */
    void quit();

    /**
     * Play the music.
     */
    void playMusic();

    /**
     * Stop the music.
     */
    void stopMusic();

}
