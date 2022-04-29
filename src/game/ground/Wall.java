package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

public class Wall extends Ground implements Jumpable{

	public Wall() {
		super('#');
	}
	private Random r = new Random();

	@Override
	public String jump(Actor actor, Location location) {
		if(actor.hasCapability(Status.TALL)){
			return actor + " jumps up the " + location.getGround() + "with no problem! Wahoo!";
		}else if(r.nextInt(10) <= 7){
			return actor + " jumps up the " + location.getGround() + "! Wahoo!";
		}else{
			actor.hurt(20);
			return  actor + " bonks the " + location.getGround() +". Took 20 fall damage. Bonk!";
		}
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
