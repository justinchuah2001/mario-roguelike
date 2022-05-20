package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class PowerWater extends Water{
    public PowerWater(){
        super("Power Water");
    }

    @Override
    public void waterEffect(Actor actor) {
        actor.addCapability(Status.POWER_UP);
    }
}
