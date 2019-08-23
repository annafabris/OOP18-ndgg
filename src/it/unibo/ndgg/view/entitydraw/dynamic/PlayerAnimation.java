package it.unibo.ndgg.view.entitydraw.dynamic;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.view.entitydraw.EntityImageAnimation;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Represents the animation of the player, it is used to move the {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
 * in the world.
 */
public class PlayerAnimation implements DynamicAnimation {

    private static final double DURATION = 0.80;

    private EntityImageAnimation currentAnimation;
    private final Player player;
    private final PlayerImage playerImage;
    private EntityState currentState;
    private EntityDirection currentDirection;
    private boolean hasAWeapon;

    /**
     * Builds a player animation.
     * @param isThePlayerOne
     *          it represents if the player is the one player of the second player
     * @param player
     *          it is the associated entity
     */
    public PlayerAnimation(final boolean isThePlayerOne, final Player player) {
        this.playerImage = new PlayerImage(isThePlayerOne);
        this.player = player;
        this.hasAWeapon = this.player.getWeapon().isPresent();
        this.setCurrentAnimation();
        this.currentState = this.player.getState();
        this.currentDirection = this.player.getCurrentDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image updatePosition() {
        this.changeAnimation(this.player.getState(), this.player.getCurrentDirection());
        this.currentAnimation.play();
        this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        return this.currentAnimation.getImage();
    }

    /**
     * It changes the current animation of the player using the state and direction of player.
     * @param state
     *          it is the current state of the player
     * @param direction
     *          it is the current direction of the player
     */
    private void changeAnimation(final EntityState state, final EntityDirection direction) {
        if (this.currentState != state || this.hasAWeapon != this.player.getWeapon().isPresent() 
                || this.currentDirection != direction) {
            this.currentAnimation.stop();
            this.setCurrentAnimation();
            this.currentState = state;
            this.currentDirection = direction;
            this.hasAWeapon = this.player.getWeapon().isPresent();
        }
    }

    /**
     * This methods set the current animation of the player.
     */
    private void setCurrentAnimation() {
        final Image image = this.playerImage.getImage(this.player.getState(), 
                                                this.player.getCurrentDirection(), 
                                                this.player.getWeapon().isPresent(), 
                                                this.player.getSwordGuard());
        this.currentAnimation = new EntityImageAnimation(image, 
                                                         playerImage.getNumberOfFrames(player.getState(),
                                                                                       player.getCurrentDirection()), 
                                                         playerImage.getFrameWidth(),
                                                         playerImage.getFrameHeight(),
                                                         Duration.seconds(DURATION),
                                                         player.getCurrentDirection());
    }
}
