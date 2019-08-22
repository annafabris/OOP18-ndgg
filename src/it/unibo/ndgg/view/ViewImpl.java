package it.unibo.ndgg.view;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Class implementation of {@link View}.
 */
public class ViewImpl implements View {

    private static final String TITLE = "Nidhogg";
    private static final Image ICON = new Image(ClassLoader.getSystemResource("images/Nidhogg.png").toExternalForm());
    private static final String THEME_SONG = "sounds/theme.mp3";
    private static final double DEFAULT_VOLUME = 20;
    private final Stage stage;
    private MediaPlayer mediaPlayer;

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
        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                quit();
            }
        });
        loadSong(THEME_SONG);
        mediaPlayer.play();
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
    public WorldView showGame() {
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
     * {@inheritDoc}
     */
    @Override
    public void muteMusic() {
        this.mediaPlayer.stop();
    }

    /**
     * {@inheritDoc}
     */
    public void playMusic() {
        this.mediaPlayer.stop();
        loadSong(THEME_SONG);
        this.mediaPlayer.play();
    }

    /**
     * Set the size of the stage depending on the visual bounds of the primary screen.
     */
    private void setScreenSize() {
        final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }


    private void loadSong(final String path) {
        mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource(path).toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(DEFAULT_VOLUME);
    }
}
