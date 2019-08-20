package it.unibo.ndgg.view;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents the view of this application. It can show the initial menu or the game view.
 */
public interface View {

    /**
     * Display the main menu.
     * @return {@link MenuView} the main menu view to load.
     */
    MenuView showMenu();

    /**
     * Display the game view.
     * @return {@link WorlView} the game view to load
     * @throws Exception
     */
    WorldView showGame();

    /**
     * Exits.
     */
    void quit();

    /**
     * Returns the View dimension.
     * @return the dimensions
     */
    Pair<Double, Double> getViewDimension();
}
