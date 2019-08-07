package it.unibo.ndgg.model.physic;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.CategoryFilter;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;

//TODO Simple Factory Pattern meglio method facotory?
/**
 * 
 *
 */
public class BodyPropertiesFactory {

    private static final long CATEGORY_PLAYER = 1;      // 000001 binary rapresentation 
    private static final long CATEGORY_DOOR = 2;        // 000010 binary rapresentation 
    private static final long CATEGORY_SWORD = 4;       // 000100 binary rapresentation 
    private static final long CATEGORY_PLATFORM = 8;    // 001000 binary rapresentation 

    private static final CategoryFilter SWORD_FILTER = new CategoryFilter(CATEGORY_SWORD, CATEGORY_SWORD
                                                                                        | CATEGORY_PLAYER
                                                                                        | CATEGORY_PLATFORM);
    private static final CategoryFilter PLAYER_FILTER = new CategoryFilter(CATEGORY_PLAYER, CATEGORY_SWORD
                                                                                        | CATEGORY_PLAYER
                                                                                        | CATEGORY_DOOR
                                                                                        | CATEGORY_PLATFORM);
    private static final CategoryFilter DOOR_FILTER = new CategoryFilter(CATEGORY_DOOR, CATEGORY_PLAYER
                                                                                        | CATEGORY_PLAYER);
    private static final CategoryFilter PLATFORM_FILTER = new CategoryFilter(CATEGORY_PLATFORM, CATEGORY_PLAYER
                                                                                        | CATEGORY_SWORD
                                                                                        | CATEGORY_PLATFORM
                                                                                        | CATEGORY_DOOR);

    private BodyPropertiesWorld physicalWorld;
    private ImmutablePair<Double, Double> worldDimensions;
 
    /**
     * Create a new {@link World} linked to the {@link BodyPropertiesWorld}.
     * @param width the width of the {@link World}
     * @param height the height of the {@link World}
     * @param bodyAssociations the class that contains all the associations necessary for the collisions to work {@link BodyAssociations}
     * @return {@link BodyPropertiesWorld}
     */
    public BodyPropertiesWorld createPhysicalWorld(final double width, final double height, final BodyAssociations bodyAssociations) {
        this.worldDimensions = new ImmutablePair<>(width, height);
        this.physicalWorld = new BodyPropertiesWorldImpl(new World(new AxisAlignedBounds(width, height)), bodyAssociations); //TODO width, height controllare
        return this.physicalWorld;
    }
 
    /**
     * 
     * @param position
     * @param width
     * @param height
     * @param type
     * @return {@link DynamicBodyProperties}
     */
    public DynamicBodyProperties createDynamicBodyProperties(final Pair<Double, Double> position, final Double width, 
            final Double height, final EntityType type) {
        final Body body;
        if (type == EntityType.PLAYER) {
            body = createBody(position, width, height, PLAYER_FILTER);
        } else if (type == EntityType.SWORD) {
            body = createBody(position, width, height, SWORD_FILTER);
        } else {
            throw new IllegalStateException("Dynamic EntityType Does not exist");
        }
        final DynamicBodyProperties dynamicBody = new DynamicBodyProperties(body);
        this.physicalWorld.putPhysicalBodyToBody(dynamicBody, body, EntityType.PLAYER);
        return dynamicBody;
    }

    /**
     * 
     * @param position
     * @param width
     * @param height
     * @param type
     * @return {@link DynamicBodyProperties}
     */
    //TODO eccezione
    public StaticBodyProperties createStaticlBodyProperties(final Pair<Double, Double> position, final Double width, 
            final Double height, final EntityType type) {
        CategoryFilter filter;
        switch (type) {
            case DOOR:
                filter = DOOR_FILTER;
                break;
            case PLATFORM:
                filter = PLATFORM_FILTER;
                break;
            default:
                throw new IllegalStateException("Static EntityType Does not exist");
        }
        final Body body = createBody(position, width, height, filter);
        final StaticBodyProperties staticBody = new StaticBodyProperties(body);
        this.physicalWorld.putPhysicalBodyToBody(staticBody, body, EntityType.SWORD);
        return staticBody;
    }

    /**
     * A private method used to create a {@link Body} and to set its position, width, height and {@link CategoryFilter}.
     * @param position 
     * @param width
     * @param height
     * @param filter {@link CategoryFilter}
     * @return {@link }
     */
    private Body createBody(final Pair<Double, Double> position, final double width, final double height, final CategoryFilter filter) {
        final Body body = new Body();
        body.addFixture(Geometry.createRectangle(width, height));
        body.translate(new Vector2(position.getLeft(), position.getRight()));
        body.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        body.getFixture(0).setFilter(filter);
        this.physicalWorld.update();
        this.physicalWorld.getWorld().addBody(body);
        return body;
    }

}
