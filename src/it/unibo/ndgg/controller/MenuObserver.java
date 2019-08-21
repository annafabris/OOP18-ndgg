package it.unibo.ndgg.controller;

/**
 * {@link MenuController} as an observer for MenuView.
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

    /**
     * Mute the music.
     */
    void mute();

    /**
     * Start the music.
     */
    void playMusic();

}
