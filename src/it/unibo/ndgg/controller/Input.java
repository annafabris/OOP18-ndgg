package it.unibo.ndgg.controller;

import static it.unibo.ndgg.controller.SimpleInput.CHANGE_GUARD;
import static it.unibo.ndgg.controller.SimpleInput.JUMP;
import static it.unibo.ndgg.controller.SimpleInput.LEFT;
import static it.unibo.ndgg.controller.SimpleInput.RIGHT;
import static it.unibo.ndgg.controller.SimpleInput.THROW;
import static it.unibo.ndgg.controller.SimpleInput.ATTACK;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
/**
 * An enumeration representing the types of input a {@link GameController} can process.
 */
public enum Input {
    /**
     * An {@link Input} that represents a {@link SimpleInput#JUMP} from player one.
     */
    JUMP_PLAYER_ONE(JUMP, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#JUMP} from player two.
     */
    JUMP_PLAYER_TWO(JUMP, PlayerID.SECOND_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#LEFT} from player one.
     */
    LEFT_PLAYER_ONE(LEFT, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#LEFT} from player two.
     */
    LEFT_PLAYER_TWO(LEFT, PlayerID.SECOND_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#RIGHT} from player one.
     */
    RIGHT_PLAYER_ONE(RIGHT, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#RIGHT} from player two.
     */
    RIGHT_PLAYER_TWO(RIGHT, PlayerID.SECOND_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#THROW} from player one.
     */
    THROW_SWORD_ONE(THROW, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#THROW} from player two.
     */
    THROW_SWORD_TWO(THROW, PlayerID.SECOND_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#CHANGE_GUARD} from player one.
     */
    CHANGE_GUARD_ONE(CHANGE_GUARD, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#CHANGE_GUARD} from player two.
     */
    CHANGE_GUARD_TWO(CHANGE_GUARD, PlayerID.SECOND_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#ATTACK} from player one.
     */
    ATTACK_PLAYER_ONE(ATTACK, PlayerID.FIRST_PLAYER),
    /**
     * An {@link Input} that represents a {@link SimpleInput#ATTACK} from player two.
     */
    ATTACK_PLAYER_TWO(ATTACK, PlayerID.SECOND_PLAYER);

    private final SimpleInput command;
    private final PlayerID player;

    Input(final SimpleInput command, final PlayerID player) {
        this.command = command;
        this.player = player;
    }

    /**
     * @return the player associated to this {@link Input}.
     */
    public PlayerID getPlayer() {
        return player;
    }

    /**
     * @return the {@link SimpleInput} associated to this {@link Input}.
     */
    public SimpleInput getCommand() {
        return command;
    }

}
