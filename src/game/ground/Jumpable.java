package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Jumpable {

    String jump(Actor actor, Location location);
}
