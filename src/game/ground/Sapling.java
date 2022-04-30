package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

/**
 * A class that represents a Sapling, the second stage of a Tree.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Tree
 * @see edu.monash.fit2099.engine.positions.Ground
 */

public class Sapling extends Tree {
    /**
     * A constructor for the Sapling class.
     * The age is initialised as 10, so it keeps increasing till the next threshold
     */
    public Sapling(){
        super('t');
        this.age = 10;
    }

    /**
     * Override of the toString method
     * @return A string with the name of the Ground
     */
    public String toString(){
        return "Sapling";
    }

    @Override
    public String jump(Actor actor, Location location) {
        String message = "";
        if(actor.hasCapability(Status.INVINCIBLE)){
            message += destroy(actor, location) + "! Money does grow on trees! A coin appeared!";

        } else if(actor.hasCapability(Status.TALL)){
            message += jumpMovement(actor, location) + " with no problem! Wahoo!";

        } else if(r.nextInt(10) <= 7){
            message += jumpMovement(actor, location) + "! Wahoo!";

        } else{
             message += jumpFailure( actor, location,20)+". Took 20 fall damage. Owie!";

        }
        return message;
    }


    /**
     * Every turn, there is a 10% chance for a coin to be generated
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(r.nextInt(10) == 0){
            location.addItem(new Coin(20));
        }
        super.tick(location);
    }
}
