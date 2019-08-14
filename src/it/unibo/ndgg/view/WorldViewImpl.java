package it.unibo.ndgg.view;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.view.entitydraw.EntityDrawer;
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
    private static final int WORLD_HEIGHT = 540;
    private static final int WORLD_WIDTH = 920;
    private static final int PLATFORM_HEIGHT = WORLD_HEIGHT / 5;
    private final Group root = new Group();
    private final Stage stage;
    private final ImageEntityAssociations images;


    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.images = new ImageEntityAssociations();
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
        Canvas canvas = new Canvas(WORLD_WIDTH, WORLD_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );


        //Image player1 = new Image("images/player_one/idle_right.png", 100, 100, false, false);
        //Image player2 = new Image("images/player_one/idle_right.png");
        final long timeStart = System.currentTimeMillis();
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
                EntityDrawer e = new EntityDrawer();
                public void handle(ActionEvent ae)
                {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
                    double x = (128 * t) % WORLD_WIDTH;

                    // Clear the canvas
                    //graphicsContext.clearRect(0, 0, 512,512);
                    // background image clears canvas
                    e.drawBackground(graphicsContext, 0);
                    e.drawMainPlatform(graphicsContext);
                    e.drawDoors(graphicsContext);
                    //graphicsContext.drawImage(player1, x, PLATFORM_HEIGHT * 4 - 90);
                    //graphicsContext.drawImage(player2, 300, PLATFORM_HEIGHT * 4 - 90, 100, 100);
                }
            });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.sizeToScene();
        return new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);
    }

}
