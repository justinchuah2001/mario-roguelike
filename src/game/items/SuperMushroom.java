package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class SuperMushroom extends Consumables implements Buyable {
    private final int price;
    public SuperMushroom(){
        super("Super Mushroom", '^', true);
        this.price= 400;
    }

    @Override
    public void consumedEffect(Actor actor) {
        super.consumedEffect(actor);
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
