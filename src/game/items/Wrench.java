package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * A weapon that can be used by an actor.
 * It also allows the actor to be able to destroy koopa shells if possible!
 * @author Justin Chuah
 * @version 1.0
 */
public class Wrench extends WeaponItem implements Buyable {
    /**
     * price of item
     */
    private final int price;

    /**
     * constructor.
     * Provides the HAS_WRENCH status which shows that the actor now possess a wrench
     */
    public Wrench(){
        super("Wrench", 'W', 50, "Bonk", 80);
        this.addCapability(Status.HAS_WRENCH);
        this.price = 200;
    }

    /**
     * This function returns the price of Wrench
     * @return Price of Wrench.
     */
    public int getPrice() {
        return price;
    }
}
