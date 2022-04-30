package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Buyable;

public class Wrench extends WeaponItem implements Buyable {
    private final int price;
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
