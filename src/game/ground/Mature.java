package game.ground;

import edu.monash.fit2099.engine.positions.Location;

public class Mature extends Tree{

    /**
     * Constructor.
     */
    public Mature() {
        super('T');
        this.age = 20;
    }

    @Override
    public void tick(Location location){
        super.tick(location);
    }
}
