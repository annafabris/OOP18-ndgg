package it.unibo.ndgg.view;

import it.unibo.ndgg.controller.MenuObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * MenuView logic for FXML references.
 */
public class MenuLogic {

    private final MenuObserver controller;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Button exitButton;

    /**
     * Builds a new {@link MenuLogic} and sets the menu controller.
     * @param menu the {@link MenuObserver} to refer to as menu controller.
     */
    public MenuLogic(final MenuObserver menu) {
        this.controller = menu;
    }

    /**
     * Load main menu screen.
     * @param event {@links ActionEvent} that triggers this method.
     */
    @FXML
    private void mainMenu(final ActionEvent event) {
        controller.mainMenu();
    }

    /**
     * Start a new game.
     * @param event {@links ActionEvent} that triggers this method.
     */
    @FXML
    private void newGame(final ActionEvent event) {
        controller.newGame();
    }

    /**
     * Load options screen.
     * @param event {@links ActionEvent} that triggers this method.
     */
    @FXML
    private void options(final ActionEvent event) {
        controller.options();
    }

    /**
     * Exits from the application.
     * @param event {@links ActionEvent} that triggers this method.
     */
    @FXML
    private void exit(final ActionEvent event) {
        controller.exit();
    }

}
