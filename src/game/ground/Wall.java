package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.items.Coin;

import java.util.Random;

/**
 * The Wall class is a class that represents a wall. Standard, impassable terrain by normal means.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see edu.monash.fit2099.engine.positions.Ground
 */

public class Wall extends Ground implements Jumpable{
	private Random r = new Random();

	/**
	 * A constructor for the Wall class
	 */
	public Wall() {
		super('#');
	}


	/**
	 * Override of the toString method
	 * @return A string with the name of the Ground
	 */
	public String toString(){
		return "Wall";
	}

	@Override
	public String jump(Actor actor, Location location) {
		String message = "";
		if(actor.hasCapability(Status.INVINCIBLE)){
			message += destroy(actor, location) + "! You're wrecking the place! A coin appeared!";

		} else if(actor.hasCapability(Status.TALL)){
			message += jumpMovement(actor, location) + " with no problem! Wahoo!";

		}else if(r.nextInt(10) <= 7){
			message += jumpMovement(actor, location) + "! Wahoo!";

		}else{
			message += jumpFailure(actor, location, 20) + ". Took 20 fall damage. Bonk!";

		}
		return message;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		if (!location.containsAnActor()){
			return new ActionList(new JumpAction(this, location, direction));
		}
		return new ActionList();
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
