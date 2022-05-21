package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.DrinkWaterAction;
import game.ground.Fountain;
import game.items.HealingWater;

public class DrinkBehaviour implements Behaviour {

    public DrinkBehaviour() {
    }

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
