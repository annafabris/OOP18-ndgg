package it.unibo.ndgg.controller;

import static it.unibo.ndgg.controller.SimpleInput.CHANGE_GUARD;
import static it.unibo.ndgg.controller.SimpleInput.JUMP;
import static it.unibo.ndgg.controller.SimpleInput.LEFT;
import static it.unibo.ndgg.controller.SimpleInput.RIGHT;
import static it.unibo.ndgg.controller.SimpleInput.THROW;
import static it.unibo.ndgg.controller.SimpleInput.ATTACK;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
/**
 * 
 */
public enum Input {
    /**
     * 
     */
    JUMP_PLAYER_ONE(JUMP, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    JUMP_PLAYER_TWO(JUMP, PlayerID.SECOND_PLAYER),
    /**
     * 
     */
    LEFT_PLAYER_ONE(LEFT, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    LEFT_PLAYER_TWO(LEFT, PlayerID.SECOND_PLAYER),
    /**
     * 
     */
    RIGHT_PLAYER_ONE(RIGHT, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    RIGHT_PLAYER_TWO(RIGHT, PlayerID.SECOND_PLAYER),
    /**
     * 
     */
    THROW_SWORD_ONE(THROW, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    THROW_SWORD_TWO(THROW, PlayerID.SECOND_PLAYER),
    /**
     * 
     */
    CHANGE_GUARD_ONE(CHANGE_GUARD, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    CHANGE_GUARD_TWO(CHANGE_GUARD, PlayerID.SECOND_PLAYER),
    /**
     * 
     */
    ATTACK_PLAYER_ONE(ATTACK, PlayerID.FIRST_PLAYER),
    /**
     * 
     */
    ATTACK_PLAYER_TWO(ATTACK, PlayerID.SECOND_PLAYER);

    private final SimpleInput command;
    private final PlayerID player;

    /*
     * 
     */
    Input(final SimpleInput command, final PlayerID player) {
        this.command = command;
        this.player = player;
    }
    
    public PlayerID getPlayer() {
        return player;
    }
    
    public SimpleInput getCommand() {
        return command;
    }

}
