package it.unibo.ndgg.view;

import java.util.ArrayList;
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
    private List<String> backgrounds = new ArrayList<>();

    public ImageEntityAssociations() {
        this.images.put(EntityType.DOOR, Stream.of("images/door_player_one.png", "images/door_player_two.png")
                .collect(Collectors.toList()));
        this.backgrounds.add(0, "images/background_1.png");
        this.backgrounds.add(1, "images/background_2.png");
        this.backgrounds.add(2, "images/background_3.png");
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
    public String getImage(final EntityType type) {
        return this.images.get(type).get(0);
    }

    /**
     * Returns the image path of the type {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param type the {@link it.unibo.ndgg.model.entity.EntityType}
     * @param imageId the number of the wanted image
     * @return path of the image in the right position
     */
    public String getImage(final EntityType type, final int imageId){
        return this.images.get(type).get(imageId);
    }

    /**
     * Returns the path of the chosen background.
     * @param backgroundId the id of the chosen background
     * @return path of the background
     */
    public String getBackground(final int backgroundId) {
        return this.backgrounds.get(backgroundId);
    }


}
