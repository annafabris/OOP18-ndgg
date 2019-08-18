package it.unibo.ndgg.view.entitydraw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import javafx.scene.image.Image;

/**
 * Represents the sword association between a sword and a image.
 */
public class SwordImage extends EntityFrameInformationImpl {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 330;
    private static final Map<Pair<EntityState, EntityDirection>, SwordFrames> STATE = new HashMap<>();
    private static final String PATH_SWORD = "images/sword/";
    private static final String EXTENSION = ".png";

    static {
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.RIGHT), SwordFrames.SWORD_RIGHT);
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.LEFT), SwordFrames.SWORD_LEFT);
        STATE.put(Pair.of(EntityState.THROWING, EntityDirection.LEFT), SwordFrames.THROW_SWORD_LEFT);
        STATE.put(Pair.of(EntityState.THROWING, EntityDirection.RIGHT), SwordFrames.THROW_SWORD_RIGHT);
        STATE.put(Pair.of(EntityState.DROPPING, EntityDirection.RIGHT), SwordFrames.SWORD_RIGHT);
        STATE.put(Pair.of(EntityState.DROPPING, EntityDirection.LEFT), SwordFrames.SWORD_LEFT);
    }

    /**
     * Builds a sword image with a frame height and a frame width.
     */
    public SwordImage() {
        super(FRAME_HEIGHT, FRAME_WIDTH);
    }

    /**
     * Returns the number of frames in a specific sprite sheet.
     * @param state 
     *          it represents the state of the sword to represent
     * @param direction
     *          it represents the direction of the sword to represent 
     * @return
     *          the number of frames in the sprite sheet
     */
    public int getNumberOfFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction)).getNumberOfFrame();
    }

    /**
     * Returns the image of one of two sword with its state and direction.
     * @param state
     *          it is the sword's state
     * @param direction
     *          it is the sword's direction
     * @return
     *          the image of one of two sword with its state and direction
     */
    public Image getImage(final EntityState state, final EntityDirection direction) {
        return new Image(this.getSwordPath(state, direction));
    }

    /**
     * Represents all possible sword's state.
     * @return
     *        a list of a pair with a state and a direction, that represents all possible sword's state
     */
    public List<Pair<EntityState, EntityDirection>> allPossibleStates() {
        return STATE.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Returns the paths of the sword Image.
     * @param state
     *         represents the state of the sword
     * @param direction
     *         represents the direction of the sword
     * @return
     *          the image and the number of frames
     */
    private String getSwordPath(final EntityState state, final EntityDirection direction) {
        return PATH_SWORD + STATE.get(Pair.of(state, direction)).getSwordSpriteSheet() + EXTENSION;
    } 
}
