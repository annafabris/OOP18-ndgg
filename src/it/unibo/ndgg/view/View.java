package it.unibo.ndgg.view;
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
    WorldView showGame() throws Exception;

    /**
     * Exits.
     */
    void quit();

}
