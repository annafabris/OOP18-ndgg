package it.unibo.ndgg.view.entitydraw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Represents the animation of the sword, it is used to move the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
 * in the world.
 */
public class SwordAnimation {

    private static final int DURATION = 120;

    private Optional<EntityImageAnimation> currentAnimation;
    private Map<Pair<EntityState, EntityDirection>, Image> images = new HashMap<>();
    private final Sword sword;
    private final SwordImage swordImage;
    private EntityState currentState;

    /**
     * Builds a sword animation.
     * @param sword
     *          it is the associated entity
     */
    public SwordAnimation(final Sword sword) {
        this.swordImage = new SwordImage();
        this.sword = sword;
        this.setImageMap();
        this.setCurrentAnimation();
        this.currentState = this.sword.getState();
    }

    /**
     * Updates the image in the animation.
     * @return
     *       the current image of the animation
     */
    public Image updatePosition() {
        this.changeAnimation(this.sword.getState());
        if (this.currentAnimation.isPresent()) {
            this.currentAnimation.get().play();
            this.currentAnimation.get().setCycleCount(1);
            return this.currentAnimation.get().getImage();
        }
        return null;
    }

    private void changeAnimation(final EntityState state) {
        if (this.currentState != state) {
            if (this.currentAnimation.isPresent()) {
                this.currentAnimation.get().stop();
            }
            this.setCurrentAnimation();
            this.currentState = state;
        }
    }

    private void setCurrentAnimation() {
        if (this.sword.getState() == EntityState.EQUIPPED) {
            this.currentAnimation = Optional.empty();
        } else {
            final Image image = this.images.get(Pair.of(this.sword.getState(),
                                                        this.sword.getCurrentDirection()));
            this.currentAnimation = Optional.of(new EntityImageAnimation(image, 
                                                                         swordImage.getNumberOfFrames(this.sword.getState(),
                                                                                                      this.sword.getCurrentDirection()), 
                                                                         swordImage.getFrameWidth(),
                                                                         swordImage.getFrameHeight(),
                                                                         Duration.millis(DURATION),
                                                                         this.sword.getCurrentDirection()));
        }
    }

    private void setImageMap() {
        this.swordImage.allPossibleStates().stream()
                                           .forEach(key -> this.images.put(key, 
                                                                           this.swordImage.getImage(key.getLeft(), 
                                                                                                    key.getRight())));
    }
}
