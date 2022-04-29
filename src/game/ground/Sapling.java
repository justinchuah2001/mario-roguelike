package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

public class Sapling extends Tree {


    /**
     * Constructor.
     *
     */
    public Sapling(){
        super('t');
        this.age = 10;
    }

    @Override
    public String jump(Actor actor, Location location) {
        if(actor.hasCapability(Status.TALL)){
            return actor + " jumps up the " + location.getGround() + "with no problem! Wahoo!";
        } else if(r.nextInt(10) <= 7){
            return actor + " jumps up the " + location.getGround() + "! Wahoo!";
        }else{
            actor.hurt(20);
            return  actor + " fails to jump the " + location.getGround() +". Took 20 fall damage. Owie!";
        }
    }


    @Override
    public void tick(Location location){
        super.tick(location);

        if(r.nextInt(10) == 0){
            location.addItem(new Coin(20));
        }
    }
}
