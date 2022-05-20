package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface Warpable {

    default String warp(Actor warper, Location source, GameMap sourceMap, Location destination, String destinationName){

        //updating the tracker for which warp pipe to return to
        ConcurrentHashMap<GameMap, Location> temp = getPreviousWarpPoint();
        temp.replace(sourceMap, temp.get(sourceMap), source);
        setPreviousWarpPoint(temp);

        //kill the actor on the other end, if there is one
        if (destination.containsAnActor()){
            Actor toBeRemoved = destination.getActor();
            destination.map().removeActor(toBeRemoved);
        }

        //move actor to new map
        destination.map().moveActor(warper, destination);

        return warper + " warps to (" + destination.x() + ", " + destination.y() + ") in " + destinationName;
    }

    HashMap<String, GameMap> getWorldList();

    ConcurrentHashMap<GameMap, Location> getPreviousWarpPoint();

    void setPreviousWarpPoint(ConcurrentHashMap<GameMap, Location> updatedHashMap);
}
