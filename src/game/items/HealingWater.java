package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public class HealingWater extends Water{

    public HealingWater(){
        super("Healing Water");
    }

    @Override
    public void waterEffect(Actor actor) {
        actor.heal(50);
    }
}
