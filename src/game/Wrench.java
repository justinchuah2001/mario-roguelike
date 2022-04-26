package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem {
    private final int price;
    public Wrench(){
        super("Wrench", 'W', 50, "Bonk", 80);
        this.addCapability(Status.HAS_WRENCH);
        this.price = 200;
    }

    public int getPrice() {
        return price;
    }
}
