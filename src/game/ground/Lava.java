package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {
    /**
     * A Constructor for the lava class.
     */
    public Lava() {
        super('L');
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            location.getActor().hurt(15);
            System.out.println(location.getActor() + " burns for 15 damage");
            if (!location.getActor().isConscious()){
                location.map().removeActor(location.getActor());
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
