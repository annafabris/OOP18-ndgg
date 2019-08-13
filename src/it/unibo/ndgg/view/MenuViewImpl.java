package it.unibo.ndgg.view;

import java.util.Optional;

import it.unibo.ndgg.controller.MenuObserver;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Class implementation of {@link MenuView}.
 */
public class MenuViewImpl implements MenuView {

    private final Stage stage;
    private Optional<MenuObserver> controller = Optional.empty();

    /**
     * Builds a new {@link MenuViewImpl} and it initializes it properly.
     * @param stage the {@link stage} in which to draw all the elements.
     */
    public MenuViewImpl(final Stage stage) {
        this.stage = stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainMenu() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void options() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final MenuObserver observer) {
        // TODO Auto-generated method stub

    }

}
