package it.unibo.ndgg.view.entitydraw;

import java.util.List;

import it.unibo.ndgg.model.entity.EntityDirection;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Represents the dynamic entity animation.
 */
public class EntityImageAnimation extends Transition {
 
    private final List<Image> images;
    private Image image;
    private final int totalFrames; 
    private final EntityDirection currentDirection;
    private int lastIndex;

    /**
     * Build an animation sprite using a sprite sheet. 
     * @param image
     *          the sprite sheet to animate
     * @param totalFrames
     *          the total of frames in the sprite sheet
     * @param frameWidth
     *          the width of the frame
     * @param frameHeight
     *          the height of the frame
     * @param duration
     *          the duration of the animation
     */
    public EntityImageAnimation(final Image image, final int totalFrames, final int frameWidth,
                                final int frameHeight, final Duration duration, final EntityDirection direction) {
        super();
        this.currentDirection = direction;
        final PixelReader pixelReader = image.getPixelReader();
        this.totalFrames = totalFrames;
        this.images = new ArrayList<>();
        for (int i = 0; i < this.totalFrames; i++) {
            final int x = i * frameWidth;
            this.images.add(new WritableImage(pixelReader, x, 0, frameWidth, frameHeight));
        }
        this.lastIndex = totalFrames - 1;
        this.image = new WritableImage(pixelReader, 0, 0, frameWidth, frameHeight);
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    /**
     * {@inheritDoc}
     */
    protected void interpolate(final double arg) {
        final int index = Math.min((int) Math.floor(arg * this.totalFrames), this.totalFrames - 1);
        if (index != lastIndex) {
            if (this.currentDirection == EntityDirection.LEFT) {
                this.lastIndex = (this.totalFrames - 1) - index;
                this.image = this.images.get(this.lastIndex);
            } else {
                this.lastIndex = index;
                this.image = this.images.get(lastIndex);
            }
        } 
    }

    /**
     * Returns the current image in the animation.
     * @return 
     *          the current image in the animation.
     */
    public Image getImage() {
        return this.image;
    }
}
