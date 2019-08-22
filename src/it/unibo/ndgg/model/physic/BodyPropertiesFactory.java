package it.unibo.ndgg.model.physic;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * A Factory to create a {@link BodyPropertiesWorld} or a {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties} or a
 * {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties}.
 */
public interface BodyPropertiesFactory {

    /**
     * Create a new {@link org.dyn4j.dynamics.World} linked to the {@link BodyPropertiesWorld}.
     * @param world the {@link it.unibo.ndgg.model.world.WorldImpl} to associate to the {@link BodyPropertiesWorld}
     * @param width the width of the {@link org.dyn4j.dynamics.World}
     * @param height the height of the {@link org.dyn4j.dynamics.World}
     * @param bodyAssociations the class that contains all the associations necessary for the collisions to work {@link BodyAssociations}
     * @return the created {@link BodyPropertiesWorld}
     */
    BodyPropertiesWorld createBodyPropertiesWorld(WorldImpl world, double width, double height, BodyAssociations bodyAssociations);

    /**
     * Creates a {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}.
     * @param position the position (x, y) of the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     * @param width the width of the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     * @param height the height of the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     * @param type {@link it.unibo.ndgg.model.entity.entitydynamic.Sword} of {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     * @return the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} created
     */
    DynamicBodyProperties createDynamicBodyProperties(Pair<Double, Double> position, Double width, Double height, EntityType type);

    /**
     * Creates a {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties}.
     * @param position the position (x, y) of the {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties}
     * @param width the width of the {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties}
     * @param height the height of the {@link it.unibo.ndgg.model.physic.body.StaticBodyProperties}
     * @param type {@link it.unibo.ndgg.model.entity.entitystatic.Door} of {@link it.unibo.ndgg.model.entity.entitystatic.Platform}
     * @return the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} created
     */
    StaticBodyProperties createStaticBodyProperties(Pair<Double, Double> position, Double width, Double height, EntityType type);
}
