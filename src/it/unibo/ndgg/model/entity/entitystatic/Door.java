package it.unibo.ndgg.model.entity.entitystatic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.physic.body.BodyProperties;

public class Door extends AbstractEntity {

	public Door(BodyProperties body) {
        super(body);
        // TODO Auto-generated constructor stub
    }

    public Optional<Player> getPlayerWhoCanOpen() {
		// TODO Auto-generated method stub
		return null;
	}

	public void hit() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public EntityType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean getDoorStatus() {
        // TODO Auto-generated method stub
        return false;
    }

}
