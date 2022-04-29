package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Goomba;

public class Sprout extends Tree {

    /**
     * Constructor.
     */
    public Sprout() {
        super('+');
        this.age = 0;
    }

    public String toString(){
        return "Sprout";
    }

    @Override
    public String jump(Actor actor, Location location) {
        if(actor.hasCapability(Status.TALL)){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + " with no problem! Boing~";
        }else if(r.nextInt(10) <= 8){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + "! Boing~";
        }else{
            actor.hurt(10);
            return  actor + " fails to jump the " + location.getGround().toString() +". Took 10 fall damage. Oof!";
        }
    }

    @Override
    public void tick(Location location){
        super.tick(location);

        if(r.nextInt(10) == 0 && !location.containsAnActor()){
            location.addActor(new Goomba());
        }
    }
}
