package game.ground;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Item {
    private int duration = 3;

    /***
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    @Override
    public void tick(Location currentLocation){
        if(currentLocation.containsAnActor()){
            currentLocation.getActor().hurt(20);
        }
        duration -= 1;
        if (duration == 0){
            currentLocation.removeItem(this);
        }
    }
}
