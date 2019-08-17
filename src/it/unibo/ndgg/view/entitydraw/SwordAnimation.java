package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Represents the animation of the sword, it is used to move the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
 * in the world.
 */
public class SwordAnimation {

    private static final int DURATION = 120;

    private EntityImageAnimation currentAnimation;
    private Sword sword;
    private SwordImage swordImage;
    private EntityState currentState;

    /**
     * Builds a sword animation.
     * @param sword
     *          it is the associated entity
     */
    public SwordAnimation(final Sword sword) {
        this.swordImage = new SwordImage();
        this.sword = sword;
        this.setCurrentAnimation();
        this.currentState = this.sword.getState();
        this.currentAnimation.play();
    }

    /**
     * Updates the image in the animation.
     */
    public void updatePosition() {
        this.changeAnimation(this.sword.getState());
        this.currentAnimation.play();
    }

    /**
     * Return the current animation.
     * @return 
     *        the current animation of the player
     */
    public EntityImageAnimation getCurrentAnimation() {
        return this.currentAnimation;
    }

    private void changeAnimation(final EntityState state) {
        if (this.currentState != state) {
            this.currentAnimation.stop();
            this.setCurrentAnimation();
            this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        }
    }

    private void setCurrentAnimation() {
        Image image = new Image(new SwordImage().getSwordPath(this.sword.getState(),
                                                              this.sword.getCurrentDirection()));
        this.currentAnimation = new EntityImageAnimation(image, 
                                                        swordImage.getNumberOfFrames(EntityState.STAYING_STILL,
                                                                                     EntityDirection.RIGHT), 
                                                        swordImage.getFrameWidth(),
                                                        swordImage.getFrameHeight(),
                                                        Duration.millis(DURATION)
                                                        this.sword.getCurrentDirection());
    }
}
