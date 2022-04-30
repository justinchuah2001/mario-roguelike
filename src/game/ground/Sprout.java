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
        String message = "";
        if(actor.hasCapability(Status.INVINCIBLE)){
            message += destroy(actor, location) + "! Bye bye Goomba spawner~ A coin appeared!";

        }
        else if(actor.hasCapability(Status.TALL)){
            message += jumpMovement(actor, location) + " with no problem! Boing~";

        }else if(r.nextInt(10) <= 8){
            message += jumpMovement(actor, location) + "! Boing~";

        }else{
            message += jumpFailure(actor, location,10) +". Took 10 fall damage. Oof!";

        }
        return message;
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
