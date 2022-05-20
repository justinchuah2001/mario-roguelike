package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Warpable;

public class WarpAction extends Action {
    private Warpable warpable;
    private Location warpableLocation;

    public WarpAction(Warpable warpable){
        this.warpable = warpable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return warpable.warp(actor, warpableLocation);
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
