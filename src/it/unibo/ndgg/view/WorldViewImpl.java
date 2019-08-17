package it.unibo.ndgg.view;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.controller.GameControllerImpl;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.view.entitydraw.BackgroundFrames;
import it.unibo.ndgg.view.entitydraw.EntityDrawer;
import it.unibo.ndgg.view.entitydraw.PlayerAnimation;
import it.unibo.ndgg.view.entitydraw.SwordAnimation;
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
    private PlayerAnimation playerAnimation1;
    private PlayerAnimation playerAnimation2;
    private SwordAnimation swordAnimation1;
    private SwordAnimation swordAnimation2;
    private Player playerL;
    private Player playerR;
    private Sword sword1;
    private Sword sword2;
    private GraphicsContext graphicsContext;


    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.viewWidth = (int) (this.stage.getWidth() - 1.0);
        this.viewHeight = (int) (this.stage.getHeight() - 1.0);
        this.entityDrawer = new EntityDrawer(new MutablePair<>(viewWidth, viewHeight), BackgroundFrames.BACKGROUND_1);
        this.timeStart = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void startGame(GameControllerImpl gameControllerImpl) {
        this.gameControllerImpl = gameControllerImpl;
        this.playerL = this.gameControllerImpl.getPlayer(0);
        this.playerR = this.gameControllerImpl.getPlayer(1);
        this.playerAnimation1 = new PlayerAnimation(true, playerL);
        this.playerAnimation2 = new PlayerAnimation(false, playerR);
        this.sword1 = this.gameControllerImpl.getSword(0);
        this.sword2 = this.gameControllerImpl.getSword(1);
        this.swordAnimation1 = new SwordAnimation(sword1);
        this.swordAnimation2 = new SwordAnimation(sword2);
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
        this.playerL.changeEntityState(EntityState.MOVING);
        this.playerR.changeEntityState(EntityState.MOVING);
        return new Scene(root, viewWidth, viewHeight);
    }

    /**
     * Draws all the static and dynamic entities.
     */
    private void draw() {
        double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
        double x1 = (128 * t) % viewWidth;
        this.entityDrawer.drawBackground(this.graphicsContext);
        this.entityDrawer.drawMainPlatform(graphicsContext);
        this.entityDrawer.drawDoors(graphicsContext);
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation1, this.playerL);
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation2, this.playerR);
        this.entityDrawer.drawSword(graphicsContext, swordAnimation1, x1, sword1.getState());
        this.entityDrawer.drawSword(graphicsContext, swordAnimation2, this.viewWidth - x1, sword2.getState());
    }
}
