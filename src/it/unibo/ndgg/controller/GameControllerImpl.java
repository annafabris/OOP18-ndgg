package it.unibo.ndgg.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.controller.commands.ChangeGuard;
import it.unibo.ndgg.controller.commands.Command;
import it.unibo.ndgg.controller.commands.Jump;
import it.unibo.ndgg.controller.commands.MoveLeft;
import it.unibo.ndgg.controller.commands.MoveRight;
import it.unibo.ndgg.controller.commands.Throw;
import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.Input;
import it.unibo.ndgg.view.SimpleInput;
import it.unibo.ndgg.view.WorldView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * {@inheritDoc}.
 */
public class GameControllerImpl implements GameController {

    private static final double FPS = 24.0;
    private final MainController controller;
    private final WorldView view; //interfaccia
    private World gameWorld;

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
        this.updateModelAndView();
    }

    /**
     * {@inheritDoc}
     */
    public void updateModelAndView() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            Duration.seconds(1 / FPS),
            new EventHandler<ActionEvent>() {
                int i = 0;
                public void handle(final ActionEvent ae) {
                    GameState gameState = gameWorld.getCurrentGameState();
                    if (gameState == GameState.PLAYERL_WON) {
                        view.playerWon(0);
                        gameLoop.stop();
                        exit();
                    } else if (gameState == GameState.PLAYERR_WON) {
                        view.playerWon(1);
                        gameLoop.stop();
                        exit();
                    } else {
                        if (i > 500) {
                            view.playerWon(0);
                            gameLoop.stop();
                            //exit();
                        //} else if (i > 50 && i < 65) {
                          //  gameWorld.movePlayer(EntityMovement.STAY_STILL_RIGHT, 0);
                            //gameWorld.movePlayer(EntityMovement.DIE_LEFT, 1);
                            //gameWorld.moveSword(EntityMovement.THROW_RIGHT, 0);
                        } else {
                            gameWorld.movePlayer(EntityMovement.MOVE_RIGHT, 0);
                            gameWorld.movePlayer(EntityMovement.MOVE_LEFT, 1);
                        }
                        i++;
                        //TODO muovi giocatore e/o spada
                        gameWorld.update();
                        view.update();
                    }
                }
            });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
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
        Command command;
        if (i.equals(SimpleInput.CHANGE_GUARD)) {
            command = new ChangeGuard(gameWorld);
            command.execute(player);
        }
        if (i.equals(SimpleInput.LEFT)) {
            command = new MoveLeft(gameWorld);
            command.execute(player);
        }
        if (i.equals(SimpleInput.RIGHT)) {
            command = new MoveRight(gameWorld);
            command.execute(player);
        }
        if (i.equals(SimpleInput.JUMP)) {
            command = new Jump(gameWorld);
            command.execute(player);
        }
        if (i.equals(SimpleInput.THROW)) {
            command = new Throw(gameWorld);
            command.execute(player);
        }
    }


}
