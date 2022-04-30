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
		if(actor.hasCapability(Status.INVINCIBLE)){

			String destroyMessage = actor + " destroys the " + location.getGround().toString() + "! You're wrecking the place! A coin appeared!";

			location.map().moveActor(actor, location);
			location.setGround(new Dirt());
			location.addItem(new Coin(5));

			return destroyMessage;
		} else if(actor.hasCapability(Status.TALL)){
			location.map().moveActor(actor, location);
			return actor + " jumps up the " + location.getGround().toString() + " with no problem! Wahoo!";
		}else if(r.nextInt(10) <= 7){
			location.map().moveActor(actor, location);
			return actor + " jumps up the " + location.getGround().toString() + "! Wahoo!";
		}else{
			actor.hurt(20);
			return actor + " bonks the " + location.getGround().toString() +". Took 20 fall damage. Bonk!";
		}
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
