package it.unibo.ndgg.controller;

import java.io.IOException;

import it.unibo.ndgg.model.levels.RoomEntities;
import it.unibo.ndgg.view.ViewImpl;
import it.unibo.ndgg.view.sounds.SoundsTypes;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
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
    public void start(final Stage stage) throws Exception {
        new MainControllerImpl(new ViewImpl(stage));
    }

    /**
     * Main method which starts the application as requested by JavaFX.
     * @param args unused
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(final String[] args) throws ClassNotFoundException, IOException {
        launch(args);
        RoomEntities rooms = new RoomEntities();
    /*    try {
            rooms.ciao();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        rooms.leggi();
       if(SoundsTypes.ATTACK==null) {
           System.out.println("ciao");
       }
//       sound.play();
       
    }
}
