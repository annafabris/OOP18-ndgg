package it.unibo.ndgg.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.controller.GameController;
import it.unibo.ndgg.controller.Input;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityState;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private static final String WIN_MESSAGE = "Congrats!";
    private static final String WIN_MESSAGE_START = "Player";
    private static final String WIN_MESSAGE_END = "won!";


    private final Group root = new Group();
    private final Stage stage;
    private final EventHandler<KeyEvent> inputHandler;
    private final List<KeyEvent> inputs;
    private final EntityDrawer entityDrawer;
    private final int viewWidth;
    private final int viewHeight;
    private Map<EntityType, List<AbstractEntity>> entities;
    private PlayerAnimation playerAnimation1;
    private PlayerAnimation playerAnimation2;
    private SwordAnimation swordAnimation1;
    private SwordAnimation swordAnimation2;
    private GraphicsContext graphicsContext;

    /**
     * Creates the class to manage the View.
     * @param stage {@link javafx.stage.Stage}.
     */
    public WorldViewImpl(final Stage stage) {
        this.stage = stage;
        this.viewWidth = (int) (this.stage.getWidth());
        this.viewHeight = (int) (this.stage.getHeight());
        this.entityDrawer = new EntityDrawer(new MutablePair<>(viewWidth, viewHeight), BackgroundFrames.BACKGROUND_1);
        this.inputs = new ArrayList<>();
        this.inputHandler = key -> this.inputs.add(key);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void startGame(final GameController controller) {
        this.entities = controller.getEntities();
        this.playerAnimation1 = new PlayerAnimation(true, (Player) this.entities.get(EntityType.PLAYER).get(0));
        this.playerAnimation2 = new PlayerAnimation(false, (Player) this.entities.get(EntityType.PLAYER).get(1));
        this.swordAnimation1 = new SwordAnimation((Sword) this.entities.get(EntityType.SWORD).get(0));
        this.swordAnimation2 = new SwordAnimation((Sword) this.entities.get(EntityType.SWORD).get(1));
        this.stage.setScene(createScene());
        this.stage.setFullScreen(true);
        this.stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, this.inputHandler);
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
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(WIN_MESSAGE);
        alert.setHeaderText(null);
        alert.setContentText(WIN_MESSAGE_START + playerID + WIN_MESSAGE_END);

        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Input> getInputs() {
        final List<Input> inputs = this.inputs.stream().map(i -> convertInput(i))
                                        .filter(i -> i.isPresent())
                                        .map(i -> i.get())
                                        .collect(Collectors.toList());
        this.inputs.clear();
        return inputs;
    }


    /**
     * Creates the new {@link javafx.scene.Scene} and calls the method 'draw'.
     * @return {@link javafx.scene.Scene}
     */
    private Scene createScene() {
        this.stage.setTitle("Nidhogg");
        final Canvas canvas = new Canvas(viewWidth, viewHeight);
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
        this.entityDrawer.drawBackground(this.graphicsContext);
        this.entityDrawer.drawMainPlatform(graphicsContext, (Platform) this.entities.get(EntityType.PLATFORM).get(0));
        this.entityDrawer.drawDoors(graphicsContext, (Door) this.entities.get(EntityType.DOOR).get(0), true);
        this.entityDrawer.drawDoors(graphicsContext, (Door) this.entities.get(EntityType.DOOR).get(1), false);
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation1, (Player) this.entities.get(EntityType.PLAYER).get(0));
        this.entityDrawer.drawPlayer(graphicsContext, playerAnimation2, (Player) this.entities.get(EntityType.PLAYER).get(1));
        final Sword sword1 = (Sword) this.entities.get(EntityType.SWORD).get(0);
        final Sword sword2 = (Sword) this.entities.get(EntityType.SWORD).get(1);
        if (sword1.getState() != EntityState.EQUIPPED) {
            this.entityDrawer.drawSword(graphicsContext, swordAnimation1, sword1.getState(), sword1);
        }
        if (sword2.getState() != EntityState.EQUIPPED) {
            this.entityDrawer.drawSword(graphicsContext, swordAnimation2, sword2.getState(), sword2);
        }
    }

    /**
     * Convert a key event in the related input if exists.
     * @param key {@KeyEvent} to convert.
     * @return the optional of the related inputs.
     */
    private Optional<Input> convertInput(final KeyEvent key) {
        if (key.getCode().equals(KeyCode.W)) {
            return Optional.of(Input.JUMP_PLAYER_ONE);
        }
        if (key.getCode().equals(KeyCode.A)) {
            return Optional.of(Input.LEFT_PLAYER_ONE);
        }
        if (key.getCode().equals(KeyCode.D)) {
            return Optional.of(Input.RIGHT_PLAYER_ONE);
        }
        if (key.getCode().equals(KeyCode.X)) {
            return Optional.of(Input.CHANGE_GUARD_ONE);
        }
        if (key.getCode().equals(KeyCode.Z)) {
            return Optional.of(Input.ATTACK_PLAYER_ONE);
        }
        if (key.getCode().equals(KeyCode.SPACE)) {
            return Optional.of(Input.THROW_SWORD_ONE);
        }
        if (key.getCode().equals(KeyCode.I)) {
            return Optional.of(Input.JUMP_PLAYER_TWO);
        }
        if (key.getCode().equals(KeyCode.J)) {
            return Optional.of(Input.LEFT_PLAYER_TWO);
        }
        if (key.getCode().equals(KeyCode.L)) {
            return Optional.of(Input.RIGHT_PLAYER_TWO);
        }
        if (key.getCode().equals(KeyCode.N)) {
            return Optional.of(Input.CHANGE_GUARD_TWO);
        }
        if (key.getCode().equals(KeyCode.M)) {
            return Optional.of(Input.THROW_SWORD_TWO);
        }
        if (key.getCode().equals(KeyCode.P)) {
            return Optional.of(Input.ATTACK_PLAYER_TWO);
        }
        return Optional.empty();
    }

}
