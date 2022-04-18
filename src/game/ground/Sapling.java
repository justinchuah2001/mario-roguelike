package game.ground;

import edu.monash.fit2099.engine.positions.Location;

public class Sapling extends Tree{


    /**
     * Constructor.
     *
     */
    public Sapling(){
        super('t');
        this.age = 10;
    }

    @Override
    public void tick(Location location){
        super.tick(location);
    }
}
