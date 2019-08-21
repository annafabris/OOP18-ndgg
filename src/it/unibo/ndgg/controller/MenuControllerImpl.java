package it.unibo.ndgg.controller;

import it.unibo.ndgg.view.MenuView;

/**
 * Class implementation of {@link MenuController}.
 */
public class MenuControllerImpl implements MenuController, MenuObserver {

    private final MainController controller;
    private final MenuView view;

    /**
     * Builds a new {@link MenuController} and load main menu screen.
     * @param view the {@link MenuView} element responsible for menu visualization.
     * @param controller the {@link MainController} responsible for the application.
     */
    public MenuControllerImpl(final MenuView view, final MainController controller) {
        this.controller = controller;
        view.setObserver(this);
        this.view = view;
        mainMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainMenu() {
        view.mainMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        try {
            controller.startNewGame();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void options() {
        view.options();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        controller.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mute() {
    }

}
