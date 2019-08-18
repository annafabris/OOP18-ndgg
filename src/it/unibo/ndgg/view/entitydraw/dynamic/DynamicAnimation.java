package it.unibo.ndgg.view.entitydraw.dynamic;

import javafx.scene.image.Image;

/**
 * Represents the animation of a {@link DynamicEntity} in the {@link World}.
 */
public interface DynamicAnimation {

    /**
     * Updates the image in the animation.
     * @return image
     *            this is the image of the animation
     */
    Image updatePosition();
}
