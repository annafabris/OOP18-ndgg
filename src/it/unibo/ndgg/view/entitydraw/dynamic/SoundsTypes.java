package it.unibo.ndgg.view.entitydraw.dynamic;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.media.AudioClip;

/**
 * An enumeration which represent all possible sounds associated with game events.
 */
public enum SoundsTypes {
        /**
         * Represent the sound which needs to be played when a player dies.
         */
        PLAYERKILLED("death"),
        /**
         * Represent the sound which needs to be played when a player hit the door.
         */
        DOORTOUCHED("door_hit"),
        /**
         * Represent  the sound which needs to be played when a player pick-up the sword.
         */
        SWORDPICKEDUP("sword_pickup"),
        /**
         * Represent  the sound which needs to be played when a player is disarmed.
         */
        PLAYERDISARMED("disarm"),
        /**
         *  Represent the sound which needs to be played when a player jumps.
         */
        JUMP("jump"),
        /**
         * Represent the sound which needs to be played when a player attack.
         */
        ATTACK("attack"),
        /**
         *  Represent the sound which needs to be played when a player throw his weapon.
         */
        THROW("throw");

    private static final String SOUNDS_PATH = "sounds/";
    private static final String SOUNDS_FORMAT = ".mp3";

    private final String soundName;

    SoundsTypes(final String soundName) {
        this.soundName = soundName;
    }

    public AudioClip getSound() {
        return new AudioClip(ClassLoader.getSystemResource(SOUNDS_PATH + soundName + SOUNDS_FORMAT).toExternalForm());
    }

}
