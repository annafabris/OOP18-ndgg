package it.unibo.ndgg.view;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.view.entitydraw.BackgroundFrames;
import it.unibo.ndgg.view.entitydraw.EntityDrawer;
import it.unibo.ndgg.view.entitydraw.StaticEntityFrames;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private static final String BACKGROUND = "images/bg1.png"; //TODO da togliere
    private final Group root = new Group();
    private final Stage stage;
    private int worldWidth;
    private int worldHeight;

    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.worldHeight = (int) this.stage.getHeight();
        this.worldWidth = (int) this.stage.getWidth() - 1;
    }

    /**
     * {@inheritDoc}.
     * @throws Exception 
     */
    @Override
    public void startGame() throws Exception {
        this.stage.setScene(loadScene());
        this.stage.show();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    private Scene loadScene() throws Exception {
        this.stage.setTitle("Nidhogg");
        Canvas canvas = new Canvas(this.worldWidth, this.worldHeight);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        EntityDrawer e = new EntityDrawer(new MutablePair<Integer, Integer>(this.worldWidth, this.worldHeight));
        e.drawBackground(graphicsContext, BackgroundFrames.BACKGROUND_2);
        e.drawMainPlatform(graphicsContext);
        e.drawDoors(graphicsContext);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        /*KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),            // 60 FPS
            new EventHandler<ActionEvent>() {
                private EntityDrawer e = new EntityDrawer();
                public void handle(ActionEvent ae) {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
                    //double x = (128 * t) % this.worldWidth;
                    // Clear the canvas
                    //graphicsContext.clearRect(0, 0, 512,512);
                    // background image clears canvas
                    e.drawBackground(graphicsContext, BackgroundFrames.BACKGROUND_1);
                    e.drawMainPlatform(graphicsContext);
                    e.drawDoors(graphicsContext);
                    //graphicsContext.drawImage(player1, x, PLATFORM_HEIGHT * 4 - 90);
                    //graphicsContext.drawImage(player2, 300, PLATFORM_HEIGHT * 4 - 90, 100, 100);
                }
            });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();*/

        stage.sizeToScene();
        return new Scene(root, this.worldWidth, this.worldHeight);
    }

}
