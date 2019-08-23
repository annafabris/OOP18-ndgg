package it.unibo.ndgg.view.entitydraw.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.view.entitydraw.EntityImageAnimation;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Represents the animation of the sword, it is used to move the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
 * in the world.
 */
public class SwordAnimation implements DynamicAnimation {

    private static final int DURATION = 50;

    private Optional<EntityImageAnimation> currentAnimation;
    private final Map<Pair<EntityState, EntityDirection>, Image> images = new HashMap<>();
    private final Sword sword;
    private final SwordImage swordImage;
    private EntityState currentState;
    private EntityDirection currentDirection;

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
     * {@inheritDoc}
     */
    public Image updatePosition() {
        this.changeAnimation(this.sword.getState(), this.sword.getCurrentDirection());
        if (this.currentAnimation.isPresent()) {
            this.currentAnimation.get().play();
            this.currentAnimation.get().setCycleCount(1);
            return this.currentAnimation.get().getImage();
        }
        return null;
    }

    /**
     * This method changes animation of the sword.
     * @param state
     *          it is the current state of the sword
     * @param direction
     *          it is the the current direction of the sword
     */
    private void changeAnimation(final EntityState state, final EntityDirection direction) {
        if (this.currentState != state || this.currentDirection != direction) {
            if (this.currentAnimation.isPresent()) {
                this.currentAnimation.get().stop();
            }
            this.setCurrentAnimation();
            this.currentDirection = direction;
            this.currentState = state;
        }
    }

    /**
     * This method set the animation of the sword.
     */
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

    /**
     * Represents all possible animation image of the sword.
     */
    private void setImageMap() {
        this.swordImage.getAllPossibleStates().stream()
                                           .forEach(key -> this.images.put(key, 
                                                                           this.swordImage.getImage(key.getLeft(), 
                                                                                                    key.getRight())));
    }
}
