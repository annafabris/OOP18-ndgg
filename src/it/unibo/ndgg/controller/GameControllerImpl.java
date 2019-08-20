package it.unibo.ndgg.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.SimpleInput;
import it.unibo.ndgg.view.WorldView;
import javafx.animation.AnimationTimer;

/**
 * {@inheritDoc}.
 */
public class GameControllerImpl implements GameController {

    private static final double FPS = 24.0;
    private final MainController controller;
    private final WorldView view; //interfaccia
    private World gameWorld;
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            updateModelAndView();
        }
    };

    public GameControllerImpl(final WorldView view ,final MainController controller, final Pair<Double, Double> worldDimension) throws Exception {
        this.controller = controller;
        this.view = view;
        this.gameWorld = new WorldImpl(worldDimension);
        game();
    }

    /**
     * {@inheritDoc}
     */
    public void game() throws Exception {
        this.gameWorld.start();
        view.startGame(this);
        this.run();
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

    private void handleInputs() {
        view.getInputs().forEach(i -> this.handle(i.getCommand(), i.getPlayer()));
    }

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
    }
    
    public void updateModelAndView() {
        GameState gameState = gameWorld.getCurrentGameState();
        if (gameState == GameState.PLAYERL_WON) {
            view.playerWon(0);
            timer.stop();
            exit();
        } else if (gameState == GameState.PLAYERR_WON) {
            view.playerWon(1);
            timer.stop();
            exit();
        } else {
            handleInputs();
            gameWorld.update();
            view.update();
        }
    }
    
    private void run() {
        timer.start();
    }


}
