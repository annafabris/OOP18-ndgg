package it.unibo.ndgg.view;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Class implementation of {@link View}.
 */
public class ViewImpl implements View {

    private static final String TITLE = "Nidhogg";
    private static final Image ICON = new Image(ClassLoader.getSystemResource("images/Nidhogg.png").toExternalForm());

    private final Stage stage;

    /**
     * Builds a new {@link ViewImpl} and it initializes it properly.
     * @param stage the {@link stage} in which to draw all the elements.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        this.stage.setTitle(TITLE);
        this.stage.getIcons().add(ICON);
        this.setScreenSize();
        this.stage.setScene(new Scene(new Pane()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuView showMenu() {
        return new MenuViewImpl(stage);
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
        Platform.exit();
    }

    /**
     * Set the size of the stage depending on the visual bounds of the primary screen.
     */
    private void setScreenSize() {
        final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setResizable(false);
    }
}
