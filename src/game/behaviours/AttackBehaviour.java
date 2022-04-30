package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour{
    private Actor target;
    private String direction;

    public AttackBehaviour(Actor target, String direction){
        this.target= target;
        this.direction = direction;

    }
    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there) {
                return new AttackAction(target, direction);
            }
        }

        return null;
    }
}
