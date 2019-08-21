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
        new MenuControllerImpl(view.showMenu(), this);
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
    public void startNewGame() {
        new GameControllerImpl(view.showGame(), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        view.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopMusic() {
        view.muteMusic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playMusic() {
        view.playMusic();
    }

}
