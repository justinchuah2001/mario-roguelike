package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class Bottle extends Consumables {

    /**
     * Constructor.
     * Adds the action that allows the actor to consume the consumable
     */
    public Bottle() {
        super("Bottle", 'b', false);
    }

    @Override
    public void consumedEffect(Actor actor) {

    }
}
