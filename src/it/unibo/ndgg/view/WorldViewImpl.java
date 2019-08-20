package it.unibo.ndgg.view;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.controller.GameControllerImpl;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.view.entitydraw.BackgroundFrames;
import it.unibo.ndgg.view.entitydraw.EntityDrawer;
import it.unibo.ndgg.view.entitydraw.dynamic.PlayerAnimation;
import it.unibo.ndgg.view.entitydraw.dynamic.SwordAnimation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private final Group root = new Group();
    private final Stage stage;
    private EntityDrawer entityDrawer;
    private GameControllerImpl gameControllerImpl;
    private final int viewWidth;
    private final int viewHeight;
    private final long timeStart;
    private Map<EntityType, List<AbstractEntity>> entities;
    private PlayerAnimation playerAnimation1;
    private PlayerAnimation playerAnimation2;
    private SwordAnimation swordAnimation1;
    private SwordAnimation swordAnimation2;
    private GraphicsContext graphicsContext;


    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.viewWidth = (int) (this.stage.getWidth());
        this.viewHeight = (int) (this.stage.getHeight());
        this.entityDrawer = new EntityDrawer(new MutablePair<>(viewWidth, viewHeight), BackgroundFrames.BACKGROUND_1);
        this.timeStart = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void startGame(final GameControllerImpl gameControllerImpl) {
        this.gameControllerImpl = gameControllerImpl;
        this.entities = this.gameControllerImpl.getEntities();
        this.playerAnimation1 = new PlayerAnimation(true, (Player) this.entities.get(EntityType.PLAYER).get(0));
        this.playerAnimation2 = new PlayerAnimation(false, (Player) this.entities.get(EntityType.PLAYER).get(1));
        this.swordAnimation1 = new SwordAnimation((Sword) this.entities.get(EntityType.SWORD).get(0));
        this.swordAnimation2 = new SwordAnimation((Sword) this.entities.get(EntityType.SWORD).get(1));
        this.stage.setScene(createScene());
        this.stage.show();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        this.draw();
    }

    /**
     * {@inheritDoc}.
     */
    public void playerWon(final int playerID) {
        //TODO da fare
        //this.stage.setScene(value);
    }


    /**
     * Creates the new {@link javafx.scene.Scene} and calls the method 'draw'.
     * @return {@link javafx.scene.Scene}
     */
    private Scene createScene() {
        this.stage.setTitle("Nidhogg");
        Canvas canvas = new Canvas(viewWidth, viewHeight);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        this.draw();
        stage.sizeToScene();
        return new Scene(root, viewWidth, viewHeight);
    }

    /**
     * Draws all the static and dynamic entities.
     */
    private void draw() {
        double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
        this.entityDrawer.drawBackground(this.graphicsContext);
        this.entityDrawer.drawMainPlatform(graphicsContext, (Platform) this.entities.get(EntityType.PLATFORM).get(0));
        this.entityDrawer.drawDoors(graphicsContext, (Door) this.entities.get(EntityType.DOOR).get(0), true);
        this.entityDrawer.drawDoors(graphicsContext, (Door) this.entities.get(EntityType.DOOR).get(1), false);
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation1, (Player) this.entities.get(EntityType.PLAYER).get(0));
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation2, (Player) this.entities.get(EntityType.PLAYER).get(1));
        Sword sword1 = (Sword) this.entities.get(EntityType.SWORD).get(0);
        Sword sword2 = (Sword) this.entities.get(EntityType.SWORD).get(1);
        this.entityDrawer.drawSword(graphicsContext, swordAnimation1, sword1.getState(), sword1);
        this.entityDrawer.drawSword(graphicsContext, swordAnimation2, sword2.getState(), sword2);
    }
}
