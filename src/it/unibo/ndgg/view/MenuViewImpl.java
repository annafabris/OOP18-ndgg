package it.unibo.ndgg.view;

import java.io.IOException;
import java.util.Optional;

import it.unibo.ndgg.controller.MenuObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Class implementation of {@link MenuView}.
 */
public class MenuViewImpl implements MenuView {

    private static final String LAYOUT_PATH = "layouts/";
    private static final String LAYOUT_EXT = ".fxml";
    private static final String MENU = LAYOUT_PATH + "MainMenu" + LAYOUT_EXT;
    private static final String OPTION = LAYOUT_PATH + "OPTIONS" + LAYOUT_EXT;

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
        changeScreen(MENU);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void options() {
        changeScreen(OPTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final MenuObserver observer) {
        controller = Optional.of(observer);
    }

    /**
     * Performs the loading of the FXML file which is located at the path received and set as root the elements loaded.
     * @param resourcePath the path of the file in which the elements to load are located
     */
    private void changeScreen(final String resourcePath) {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(resourcePath));
        loader.setController(new MenuLogic(controller.get()));
        try {
            final Parent root = loader.load();
            this.stage.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.stage.show();

    }

}
