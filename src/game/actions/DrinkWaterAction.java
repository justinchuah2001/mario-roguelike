package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.items.HealingWater;
import game.items.PowerWater;
import game.items.Water;

import java.util.ArrayList;

public class DrinkWaterAction extends Action {
    private Water water;

    public DrinkWaterAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Status.HAS_BOTTLE)) {
            ArrayList<Water> bottleContent = Bottle.getInstance().getBottleContent();
            if (!bottleContent.isEmpty()) {
                this.water = bottleContent.get(bottleContent.size() - 1);
                this.water.waterEffect(actor);
                bottleContent.remove(bottleContent.size() - 1);
            }
        }else if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if(map.locationOf(actor).getGround().hasCapability(Status.HEALING)){
                this.water = new HealingWater();

            }else if (map.locationOf(actor).getGround().hasCapability(Status.POWERING)){
                this.water = new PowerWater();
            }
            this.water.waterEffect(actor);
            map.locationOf(actor).getGround().addCapability(Status.DRANK_FROM);
        }
        return actor + " drank " + this.water;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water in the bottle. " + Bottle.getInstance();
    }

}
