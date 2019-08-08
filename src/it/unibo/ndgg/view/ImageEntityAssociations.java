package it.unibo.ndgg.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ndgg.model.entity.EntityType;
/**
 * An association between {@link it.unibo.ndgg.model.entity.EntityType} and the path to get the images.
 */
public class ImageEntityAssociations {

    private Map<EntityType, List<String>> images = new HashMap<>();

    public ImageEntityAssociations() {
        images.put(EntityType.DOOR, Stream.of("images/doorTest.png").collect(Collectors.toList()));
        // IMAGES.put(EntityType.PLATFORM, Stream.of().collect(Collectors.toList()));
        // IMAGES.put(EntityType.POWERUP, Stream.of().collect(Collectors.toList()));
    }

    /**
     * Add one image path.
     * @param type the {@link it.unibo.ndgg.model.entity.EntityType} of the image
     * @param image the path of the image
     */
    public void setImage(final EntityType type, final String image) {
        this.images.get(type).add(image);
    }

    /**
     * Returns the image path of the type {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param type the {@link it.unibo.ndgg.model.entity.EntityType}
     * @return path of first the image
     */
    String getImage(final EntityType type){
        return this.images.get(type).get(0);
    }

    /**
     * Returns the image path of the type {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param type the {@link it.unibo.ndgg.model.entity.EntityType}
     * @param imageId the number of the wanted image
     * @return path of the image in the right position
     */
    String getImage(final EntityType type, final int imageId){
        return this.images.get(type).get(imageId);
    }


}
