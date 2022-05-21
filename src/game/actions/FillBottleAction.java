package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.items.HealingWater;
import game.items.PowerWater;

public class FillBottleAction extends Action {

    public FillBottleAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.locationOf(actor).getGround().hasCapability(Status.HEALING)){
            Bottle.getInstance().fillBottle(new HealingWater());
        }else if (map.locationOf(actor).getGround().hasCapability(Status.POWERING)){
            Bottle.getInstance().fillBottle(new PowerWater());
        }
        map.locationOf(actor).getGround().addCapability(Status.WAS_COLLECTED);
        return actor + " has filled the bottle with water from the fountain! ";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fill bottle with water from the fountain. ";
    }
}
