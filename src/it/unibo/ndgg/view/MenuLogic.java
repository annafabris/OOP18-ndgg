package it.unibo.ndgg.view;

import it.unibo.ndgg.controller.MenuObserver;

/**
 * MenuView logic for FXML references.
 */
public class MenuLogic {

    private final MenuObserver controller;

    /**
     * Builds a new {@link MenuViewImpl} and it initializes it properly.
     * @param menu the {@link MenuObserver} to refer to as menu controller.
     */
    public MenuLogic(final MenuObserver menu) {
        this.controller = menu;
    }

}
