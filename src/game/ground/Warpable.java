package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Warpable {

    default String warp(Actor actor, Location location){

        return "Travelling to ";
    }

    default String warpMovement(Actor actor, Location location){
        return null;
    }
}
