package it.unibo.ndgg.controller;

import it.unibo.ndgg.view.View;

/**
 * Class implementation of {@link MainController}.
 */
public class MainControllerImpl implements MainController {

    private final View view;

    /**
     * Builds a new {@link MainControllerImpl} and load main menu.
     * @param view the {@link View} element responsible for the application.
     */
    public MainControllerImpl(final View view) {
        this.view = view;
        menu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void menu() {
        new MenuControllerImpl(view.showMenu(), this);
    }

    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    @Override
    public void startNewGame() throws Exception {
        new GameControllerImpl(view.showGame(), this, this.view.getViewDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        quitGame();
        view.quit();
    }

    /**
     * Stops the game controller.
     */
    private void quitGame() {
        // TODO Auto-generated method stub
    }

}
