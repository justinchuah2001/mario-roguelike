package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Goomba;
import game.items.Coin;

/**
 * A class that represents a Sprout, the first stage of a Tree.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Tree
 * @see edu.monash.fit2099.engine.positions.Ground
 */

public class Sprout extends Tree {

    /**
     * A constructor for the Sprout class.
     * The age is initialised as 0
     */
    public Sprout() {
        super('+');
        this.age = 0;
    }

    /**
     * Override of the toString method
     * @return A string with the name of the Ground
     */
    public String toString(){
        return "Sprout";
    }

    @Override
    public String jump(Actor actor, Location location) {
        if(actor.hasCapability(Status.INVINCIBLE)){
            String destroyMessage = actor + " destroys the " + location.getGround().toString() + "! Bye bye Goomba spawner~ A coin appeared!";

            location.map().moveActor(actor, location);
            location.setGround(new Dirt());
            location.addItem(new Coin(5));

            return destroyMessage;
        }
        else if(actor.hasCapability(Status.TALL)){
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

    /**
     * Every turn, there is a 10% chance for a Goomba to spawn when there is no actor on this Sprout
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(r.nextInt(10) == 0 && !location.containsAnActor()){
            location.addActor(new Goomba());
        }

        super.tick(location);
    }
}
