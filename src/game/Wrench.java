package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem {
    public Wrench(){
        super("Wrench", 'W', 50, "Bonk", 80);
        this.addCapability(Status.HAS_WRENCH);
    }
}
