package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Bottle;
import game.items.HealingWater;
import game.items.PowerWater;
import game.items.Water;

public class FillBottleAction extends Action {
    private Location location;
    private int remainingWater;
    private Water waterType;
    public FillBottleAction(Location location, int remainingWater){
        this.location = location;
        this.remainingWater = remainingWater;
        getWaterType();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.locationOf(actor).getGround().hasCapability(Status.HEALING)){
            Bottle.getInstance().fillBottle(new HealingWater());
        }else if (map.locationOf(actor).getGround().hasCapability(Status.POWERING)){
            Bottle.getInstance().fillBottle(new PowerWater());
        }
        map.locationOf(actor).getGround().addCapability(Status.WAS_COLLECTED);
        return actor + " has filled the bottle with " + this.waterType;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fill bottle with " + this.waterType + " ("+ this.remainingWater + "/10) ";
    }

    public void getWaterType(){
        if (this.location.getGround().hasCapability(Status.HEALING)){
            this.waterType = new HealingWater();
        } else if (this.location.getGround().hasCapability(Status.POWERING)){
            this.waterType = new PowerWater();
        }
    }
}
