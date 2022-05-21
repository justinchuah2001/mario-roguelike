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

/**
 * This action allows actors to drink the water from the fountain through two ways:
 * One of it being directly drank by enemies .
 * Another being through the bottle that was filled with water from fountain.
 * @author Justin Chuah
 * @version 1.0
 * @see game.actions.FillBottleAction
 * @see game.items.Bottle
 */

public class DrinkWaterAction extends Action {
    /**
     * Water that was drank by actors.
     */
    private Water water;

    /**
     * Constructor
     */
    public DrinkWaterAction(){
    }

    /**
     * This function allows the actor to drink and gain the effects of the water drank
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of which actor drank what kind of water
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Status.HAS_BOTTLE)) { //If actor holds a bottle, drink the last index of the bottle(array list)
            ArrayList<Water> bottleContent = Bottle.getInstance().getBottleContent();
            if (!bottleContent.isEmpty()) {
                water = bottleContent.get(bottleContent.size() - 1);
                water.waterEffect(actor);
                bottleContent.remove(bottleContent.size() - 1);
            }
        }else if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){ //If actor is not player, actor gains effects from fountain directly
            if(map.locationOf(actor).getGround().hasCapability(Status.HEALING)){
                water = new HealingWater();

            }else if (map.locationOf(actor).getGround().hasCapability(Status.POWERING)){
                water = new PowerWater();
            }
            water.waterEffect(actor);
            map.locationOf(actor).getGround().addCapability(Status.DRANK_FROM);
        }
        return actor + " drank " + water;
    }

    /**
     * This function returns the menu description of the action.
     * @param actor The actor performing the action.
     * @return Menu description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water in the bottle. " + Bottle.getInstance();
    }

}
