package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * A magical mushroom that increases the maximum health points of the consumer while also providing some bonus effects!
 * @author Justin Chuah
 * @version 1.0
 */
public class SuperMushroom extends Consumables implements Buyable {
    /**
     * Price of the item
     */
    private final int price;

    /**
     * Constructor.
     */
    public SuperMushroom(){
        super("Super Mushroom", '^', true);
        this.price= 400;
    }

    /**
     * Effects on the actor after consuming
     * Increases the maximum health points of the consumer and provides the TALL status.
     * @param actor Actor consuming
     */
    @Override
    public void consumedEffect(Actor actor) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
    }

    /**
     * This function returns the price of SuperMushroom
     * @return Price of SuperMushroom.
     */
    public int getPrice() {
        return price;
    }
}
