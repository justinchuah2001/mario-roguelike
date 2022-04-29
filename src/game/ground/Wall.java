package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;

import java.util.Random;

public class Wall extends Ground implements Jumpable{

	public Wall() {
		super('#');
	}
	private Random r = new Random();

	public String toString(){
		return "Wall";
	}

	@Override
	public String jump(Actor actor, Location location) {
		if(actor.hasCapability(Status.TALL)){
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
