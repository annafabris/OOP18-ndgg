package it.unibo.ndgg.controller;

import java.util.List;
import java.util.Map;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.WorldView;
import javafx.animation.AnimationTimer;

/**
 * {@inheritDoc}.
 */
public class GameControllerImpl implements GameController {

    private static final String LEFT_PLAYER_MSG = "Blue Player Won!";
    private static final String RIGHT_PLAYER_MSG = "Green Player Won!";
    private final MainController controller;
    private final WorldView view;
    private final World gameWorld;
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            updateModelAndView();
        }
    };

    /**
     * Builds a new {@link gameControllerImpl} and start a new game.
     * @param view the {@link WorldView} element responsible for the application.
     * @param controller the {@link MainController} responsible for the application.
     */
    public GameControllerImpl(final WorldView view, final MainController controller) {
        this.controller = controller;
        this.view = view;
        this.gameWorld = new WorldImpl();
        run();
    }

    /**
     * {@inheritDoc}
     */
    public void newGame() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModelAndView() {
        final GameState gameState = gameWorld.getCurrentGameState();
        if (gameState == GameState.PLAYERL_WON) {
            timer.stop();
            view.playerWon(LEFT_PLAYER_MSG);
            controller.menu();
        } else if (gameState == GameState.PLAYERR_WON) {
            timer.stop();
            view.playerWon(RIGHT_PLAYER_MSG);
            controller.menu();
        } else {
            handleInputs();
            gameWorld.update();
            view.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void exit() {
        controller.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<EntityType, List<AbstractEntity>> getEntities() {
        return this.gameWorld.getEntities();
    }

    /**
     * Handle all the inputs received.
     */
    private void handleInputs() {
        view.getInputs().forEach(i -> this.handle(i.getCommand(), i.getPlayer()));
    }

    /*
     * Handle an input and notify model.
     */
    private void handle(final SimpleInput i, final PlayerID player) {
        if (i.equals(SimpleInput.CHANGE_GUARD)) {
            gameWorld.changeGuard(player);
        }
        if (i.equals(SimpleInput.LEFT)) {
            gameWorld.movePlayerLeft(player);
        }
        if (i.equals(SimpleInput.RIGHT)) {
            gameWorld.movePlayerRight(player);
        }
        if (i.equals(SimpleInput.JUMP)) {
            gameWorld.jumpPlayer(player);
        }
        if (i.equals(SimpleInput.THROW)) {
            gameWorld.throwSword(player);
        }
        if (i.equals(SimpleInput.ATTACK)) {
            gameWorld.attackPlayer(player);
        }
    }

    /**
     * Run the game.
     */
    private void run() {
        this.gameWorld.start();
        view.startGame(this);
        timer.start();
    }
}
