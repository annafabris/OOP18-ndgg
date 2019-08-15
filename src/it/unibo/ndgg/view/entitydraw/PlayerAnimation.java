package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerAnimation {
    
    private static final int DURATION = 120;
    
    private EntityImageAnimation currentAnimation;
    private PlayerImage player;

    public PlayerAnimation(boolean isThePlayerOne) {
        this.player = new PlayerImage();
        if (isThePlayerOne) {
           this.setCurrentAnimation(EntityState.STAYING_STILL, EntityDirection.LEFT, true, SwordGuard.LOW);                                        
        } else {
            this.setCurrentAnimation(EntityState.STAYING_STILL, EntityDirection.RIGHT, true, SwordGuard.LOW);
        }
        this.currentAnimation.play();
    }
    
    //To do: aggiungi anche entity e associa ad una entity
    public void updatePosition(final EntityState state, final EntityDirection direction, 
                          final boolean hasAWeapon, final SwordGuard guard) {
        this.changeAnimation(state, direction, hasAWeapon, guard);
    }
    
    
    private void changeAnimation(final EntityState state, final EntityDirection direction, 
                                 final boolean hasAWeapon, final SwordGuard guard) {
        this.currentAnimation.stop();
        this.setCurrentAnimation(state, direction, hasAWeapon, guard);
        this.currentAnimation.play();
        this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        
    }

    private void setCurrentAnimation(final EntityState state, final EntityDirection direction, 
                                      final boolean hasAWeapon, final SwordGuard guard) {
        Image image = new Image(new PlayerImage().getPlayer1Path(state, direction, hasAWeapon, guard));
        this.currentAnimation = new EntityImageAnimation(image, 
                                                         player.getNumberOfFrames(EntityState.STAYING_STILL, 
                                                                                  EntityDirection.RIGHT),
                                                         player.getFrameWidth(),
                                                         player.getFrameHeight(),
                                                         Duration.millis(DURATION));  
    }


}
