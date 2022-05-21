package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Warpable interface is implemented by actors that can warp
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.1
 * @see game.ground.WarpPipe
 * @see game.actors.Player
 */
public interface Warpable {

    /**
     * Moves the actor from one map to another, while saving the location of the WARP_POINT entered, to return the
     * actor to when traversing back
     *
     * @param warper the actor warping
     * @param source the start location
     * @param sourceMap the starting map
     * @param destination the destination location
     * @param destinationName the name of the map being travelled to, for UI message purposes
     * @return a string detailing where the actor warped to, and to which map
     */
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

    /**
     * a getter for the worldList hashmap
     * @return a HashMap containing the names and GameMaps of the game maps
     */
    HashMap<String, GameMap> getWorldList();

    /**
     * a getter for the concurrentHashMap for previous warp points
     * @return a ConcurrentHashMap containing the last used location that the warper was in the respective gameMaps
     */
    ConcurrentHashMap<GameMap, Location> getPreviousWarpPoint();

    /**
     * a setter for the previous warp point ConcurrentHashMap
     * @param updatedHashMap the updated HashMap
     */
    void setPreviousWarpPoint(ConcurrentHashMap<GameMap, Location> updatedHashMap);
}
