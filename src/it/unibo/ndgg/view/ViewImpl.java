package it.unibo.ndgg.view;

import javafx.stage.Stage;

/**
 * Class implementation of {@link View}.
 */
public class ViewImpl implements View {

    private final Stage stage;

    /**
     * Builds a new {@link ViewImpl}.
     * @param stage the {@link stage} of JavaFX application.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuView showMenu() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldView showGame() throws Exception {
        return new WorldViewImpl(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }

}
