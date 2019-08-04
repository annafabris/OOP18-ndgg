package it.unibo.ndgg.view.entitydraw;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityState;
import javafx.scene.image.Image;

/**
 * Represents the sprite sheet of one of the two {@link Player} in the {@link World}.
 */
public class BlueCuteBoyImage {

    private static final Map<EntityState, Pair<Image, Integer>> STATE = new HashMap<>();
    private static final  String PATH;

    static {
        PATH = "images/player_one";
        STATE.put(EntityState.MOVING_LEFT, Pair.of(new Image(PATH + "run_left.png"), 5));
        STATE.put(EntityState.MOVING_RIGHT, Pair.of(new Image(PATH + "run_right.png"), 5));
    }

    private String getPath() {
        return "images/player_one";
    }
}
