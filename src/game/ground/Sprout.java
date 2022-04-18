package game.ground;

import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree {

    /**
     * Constructor.
     */
    public Sprout() {
        super('+');
        this.age = 0;
    }

    @Override
    public void tick(Location location){
        super.tick(location);
    }
}
