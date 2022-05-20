package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Warpable;

public class WarpAction extends Action {
    private Warpable warpable;
    private Location source;
    private GameMap sourceMap;
    private Location destination;
    private String destinationName;

    public WarpAction(Warpable warper, Location source, GameMap sourceMap,
                      Location destination, String destinationName){
        this.warpable = warper;
        this.source = source;
        this.sourceMap = sourceMap;
        this.destination = destination;
        this.destinationName = destinationName;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return warpable.warp(actor, source, sourceMap, destination, destinationName);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " warps to " + destinationName;
    }
}
