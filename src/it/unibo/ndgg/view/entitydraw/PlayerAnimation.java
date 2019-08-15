package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Represents the animation of the player, it is used to move the {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
 * in the world.
 */
public class PlayerAnimation {

    private static final int DURATION = 120;

    private EntityImageAnimation currentAnimation;
    private Player player;
    private PlayerImage playerImage;
    private EntityState currentState;

    /**
     * Builds a player animation.
     * @param isThePlayerOne
     *          it represents if the player is the one player of the second player
     * @param player
     *          it is the associated entity
     */
    public PlayerAnimation(final boolean isThePlayerOne, final Player player) {
        this.playerImage = new PlayerImage();
        this.player = player;
        if (isThePlayerOne) {
            this.setCurrentAnimation(); 
        } else {
            this.setCurrentAnimation();
        }
        this.currentState = this.player.getState();
        this.currentAnimation.play();
    }

    /**
     * Updates the image in the animation.
     */
    public void updatePosition() {
        this.changeAnimation(this.player.getState());
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
        Image image = new Image(new PlayerImage().getPlayer1Path(this.player.getState(), 
                                                                 this.player.getCurrentDirection(), 
                                                                 this.player.getWeapon().isPresent(), 
                                                                 this.player.getSwordGuard().get()));
        this.currentAnimation = new EntityImageAnimation(image, 
                                                         playerImage.getNumberOfFrames(EntityState.STAYING_STILL,
                                                                                       EntityDirection.RIGHT), 
                                                         playerImage.getFrameWidth(),
                                                         playerImage.getFrameHeight(),
                                                         Duration.millis(DURATION));
    }


}
