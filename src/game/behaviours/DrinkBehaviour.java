package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.DrinkWaterAction;

/**
 * Class that allows actors aside from Player to drink from the fountain whenever possible.
 * @author Justin Chuah
 * @version 1.0
 * @see game.behaviours.Behaviour
 * @see game.ground.Fountain
 */
public class DrinkBehaviour implements Behaviour {
    /**
     * Constructor
     */
    public DrinkBehaviour() {
    }

    /**
     * Return drink water action if the fountain is not depleted
     * Return null otherwise
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return Action or null if no fountain or fountain is depleted.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.locationOf(actor).getGround().hasCapability(Status.IS_DEPLETED) && map.locationOf(actor).getGround().hasCapability(Status.ON_FOUNTAIN)) {
            return new DrinkWaterAction();
        }
        else{
            return null;
        }
    }
}
