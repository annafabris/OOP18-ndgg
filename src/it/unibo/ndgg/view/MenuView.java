package it.unibo.ndgg.view;

import it.unibo.ndgg.controller.MenuObserver;

/**
 * Represents the view of the main menu of this application.
 */
public interface MenuView {

    /**
     * Load the main menu screen.
     */
    void mainMenu();

    /**
     * Load the options screen.
     */
    void options();

    /**
     * Link the current view with a {@link MenuObserver}.
     * @param observer the @MenuObserver to which this view is linked.
     */
    void setObserver(MenuObserver observer);

}
