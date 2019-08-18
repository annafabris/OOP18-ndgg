package it.unibo.ndgg.view.entitydraw;

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

    private static final double DURATION = 0.100;

    private EntityImageAnimation currentAnimation;
    private final Player player;
    private final PlayerImage playerImage;
    private EntityState currentState;
    private final boolean isThePlayerOne;
    private boolean hasAWeapon;

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
        this.hasAWeapon = this.player.getWeapon().isPresent();
        this.isThePlayerOne = isThePlayerOne;
        this.setCurrentAnimation();
        this.currentState = this.player.getState();
    }

    /**
     * Updates the image in the animation.
     * @return image
     *            this is the image of the animation
     */
    public Image updatePosition() {
        this.changeAnimation(this.player.getState());
        this.currentAnimation.play();
        this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        return this.currentAnimation.getImage();
    }

    private void changeAnimation(final EntityState state) {
        if (this.currentState != state || this.hasAWeapon != this.player.getWeapon().isPresent()) {
            this.currentAnimation.stop();
            this.setCurrentAnimation();
            this.currentState = state;
            this.hasAWeapon = this.player.getWeapon().isPresent();
        }
    }

    private void setCurrentAnimation() {
        Image image = this.playerImage.getImage(this.player.getState(), 
                     this.player.getCurrentDirection(), 
                     this.player.getWeapon().isPresent(), 
                     this.player.getSwordGuard(),
                     this.isThePlayerOne);
        this.currentAnimation = new EntityImageAnimation(image, 
                                                         playerImage.getNumberOfFrames(player.getState(),
                                                                                       player.getCurrentDirection()), 
                                                         playerImage.getFrameWidth(),
                                                         playerImage.getFrameHeight(),
                                                         Duration.seconds(DURATION),
                                                         player.getCurrentDirection());
    }
}
