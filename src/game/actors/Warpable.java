package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.ground.WarpPipe;

public interface Warpable {

    default String warp(Actor warper, Location at){
        if (at.getActor() == warper && at.getGround().hasCapability(Status.WARP_POINT)){
        }
        return null;
    }

    default String warpMovement(Actor actor, Location destination, GameMap destinationMap){
        destination.map().moveActor(actor, destinationMap.at(destination.x(), destination.y()));
        return actor.toString();
    }
}
