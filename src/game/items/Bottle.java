package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class Bottle extends Consumables {

    /**
     * Constructor.
     * Adds the action that allows the actor to consume the consumable
     *
     * @param name        Name of the consumable item
     * @param displayChar Display character of the item on the map
     * @param portable    Boolean to determine whether item is droppable and pickable
     */
    public Bottle(String name, char displayChar, boolean portable) {
        super("Bottle", 'b', false);
    }

    @Override
    public void consumedEffect(Actor actor) {

    }
}
