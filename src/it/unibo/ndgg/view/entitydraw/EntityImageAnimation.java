package it.unibo.ndgg.view.entitydraw;

import java.util.List;
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
 
    private final PixelReader pixelReader;
    private final List<Image> images;
    private Image image;
    private final int totalFrames; 
    private final int frameWidth; 
    private final int frameHeight; 
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
                                final int frameHeight, final Duration duration) {
        super();
        this.pixelReader = image.getPixelReader();
        this.totalFrames = totalFrames;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.images = new ArrayList<>();
        for (int i = 0; i < this.totalFrames; i++) {
            final int x = i * this.frameWidth;
            this.images.add(new WritableImage(this.pixelReader, x, 0, this.frameWidth, this.frameHeight));
        }
        this.lastIndex = 0;
        this.image = new WritableImage(this.pixelReader, 0, 0, this.frameWidth, this.frameHeight);
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    /**
     * {@inheritDoc}
     */
    protected void interpolate(final double arg) {
        final int index = Math.min((int) Math.floor(arg * this.totalFrames), this.totalFrames - 1);
        if (index != lastIndex) {
            this.lastIndex = index;
            this.image = this.images.get(lastIndex);
        }
    }

    /**
     * 
     * @return
     */
    public Image getImage() {
        return this.image;
    }
}
