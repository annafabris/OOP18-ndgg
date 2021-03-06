package it.unibo.ndgg.controller;

import it.unibo.ndgg.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class which starts the whole application.
 */
public class Nidhogg extends Application {
    /**
     * The method used by JavaFX to start the application. It creates the main controller of
     * this application.
     * @param stage the {@link Stage} to use for this application
     */
    @Override
    public void start(final Stage stage) {
        new MainControllerImpl(new ViewImpl(stage));
    }

    /**
     * Main method which starts the application as requested by JavaFX.
     * @param args unused
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
