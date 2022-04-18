package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Koopa;

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

        if(r.nextInt(20) < 3 && !location.containsAnActor()){
            location.addActor(new Koopa());
        }
    }
}
