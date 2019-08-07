package it.unibo.ndgg.view.entitydraw;

/**
 * Represent a generic frame information getter about an entity.
 */
public class EntityFrameInformationImpl implements EntityFrameInfomation {

    private final int frameHeight;
    private final int frameWidth;

    /**
     * Builds a generic entity frame information.
     * @param frameHeight
     *          the height of the frame
     * @param frameWidth
     *          the width of the frame
     */
    public EntityFrameInformationImpl(final int frameHeight, final int frameWidth) {
        super();
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFrameHeight() {
        return this.frameHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFrameWidth() {
        return this.frameWidth;
    }

}
