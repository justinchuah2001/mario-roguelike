package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Bottle;
import game.water.HealingWater;
import game.water.PowerWater;
import game.water.Water;

/**
 * This action allows to fill the bottle with water from the fountain
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.items.Bottle
 */
public class FillBottleAction extends Action {
    /**
     * Location of the actor, which is also the same location of the fountain
     */
    private final Location location;
    /**
     * Integer that shows the remaining amount of water.
     */
    private final int remainingWater;
    /**
     * Type of water that is going to be filled in the bottle
     */
    private Water waterType;

    /**
     * Constructor for the fill bottle action
     * @param location location of the fountain
     * @param remainingWater Integer that shows the remaining amount of water.
     */
    public FillBottleAction(Location location, int remainingWater){
        this.location = location;
        this.remainingWater = remainingWater;
        getWaterType();
    }

    /**
     * This function let an Actor fills a bottle with water
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description whether the actor successfully fileld the bottle with water
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.locationOf(actor).getGround().hasCapability(Status.HEALING)){ // If fountain is healing fountain, store healing water
            Bottle.getInstance().fillBottle(new HealingWater());
        }else if (map.locationOf(actor).getGround().hasCapability(Status.POWERING)){ // If fountain is power fountain, store power water
            Bottle.getInstance().fillBottle(new PowerWater());
        }
        map.locationOf(actor).getGround().addCapability(Status.WAS_COLLECTED); // Indicate that the fountain's water was collected
        return actor + " has filled the bottle with " + waterType;
    }

    /**
     * This function returns the menu description of the action.
     * @param actor The actor performing the action.
     * @return Menu description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fill bottle with " + waterType + " ("+ remainingWater + "/10) ";
    }

    /**
     * Gets the type of water of the fountain.
     */
    public void getWaterType(){
        if (location.getGround().hasCapability(Status.HEALING)){
            waterType = new HealingWater();
        } else if (location.getGround().hasCapability(Status.POWERING)){
            waterType = new PowerWater();
        }
    }
}
