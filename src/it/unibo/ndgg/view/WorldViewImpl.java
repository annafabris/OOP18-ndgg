package it.unibo.ndgg.view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityFactory;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.entitydraw.BackgroundFrames;
import it.unibo.ndgg.view.entitydraw.EntityDrawer;
import it.unibo.ndgg.view.entitydraw.PlayerAnimation;
import it.unibo.ndgg.view.entitydraw.StaticEntityFrames;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private final Group root = new Group();
    private final Stage stage;
    private EntityDrawer entityDrawer;
    private final int viewWidth;
    private final int viewHeight;

    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.viewWidth = (int) (this.stage.getWidth() - 1.0);
        this.viewHeight = (int) (this.stage.getHeight() - 1.0);
        this.entityDrawer = new EntityDrawer(new MutablePair<>(viewWidth, viewHeight));
    }

    /**
     * {@inheritDoc}.
     * @throws Exception 
     */
    @Override
    public void startGame() throws Exception {
        this.stage.setScene(loadStaticEntities());
        this.stage.show();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        this.loadStaticEntities();
    }

    /**
     * {@inheritDoc}.
     */
    public void PlayerWon(final int PlayerID) {
        //TODO da fare
        //this.stage.setScene(value);
    }

    private Scene loadStaticEntities() {
        this.stage.setTitle("Nidhogg");
        Canvas canvas = new Canvas(viewWidth, viewHeight);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        BodyPropertiesFactory bodyPropertiesFactory = new BodyPropertiesFactory();
        BodyPropertiesWorld bodyPropertiesWorld = bodyPropertiesFactory.createBodyPropertiesWorld(new WorldImpl(), 960, 450, new BodyAssociations());

        EntityFactory entityFactory = new EntityFactoryImpl(bodyPropertiesFactory);
        Player player1 = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(100.0, 400.0), EntityDirection.RIGHT);

        this.entityDrawer.drawBackground(graphicsContext, BackgroundFrames.BACKGROUND_1);
        this.entityDrawer.drawMainPlatform(graphicsContext);
        this.entityDrawer.drawDoors(graphicsContext);

        PlayerAnimation playerAnimation1 = new PlayerAnimation(true, player1);
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation1, 128);
        player1.changeEntityState(EntityState.MOVING);

        //TODO va messo 
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.17),            // 60 FPS
            new EventHandler<ActionEvent>() {
                private EntityDrawer e = new EntityDrawer(new MutablePair<Integer, Integer>
                    (1360, 760));

                public void handle(ActionEvent ae) {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
                    double x1 = (128 * t) % viewWidth;
                    // Clear the canvas
                    graphicsContext.clearRect(0, 0, viewWidth, viewHeight);
                    //background image clears canvas
//                    e.drawBackground(graphicsContext, BackgroundFrames.BACKGROUND_1);
                    e.drawMainPlatform(graphicsContext);
                    e.drawDoors(graphicsContext);
                    e.drawPlayer(graphicsContext, playerAnimation1, x1);
                    //graphicsContext.drawImage(player1, x, PLATFORM_HEIGHT * 4 - 90);
                    //graphicsContext.drawImage(player2, 300, PLATFORM_HEIGHT * 4 - 90, 100, 100);
                }
            });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.sizeToScene();
        return new Scene(root, viewWidth, viewHeight);
    }

}
