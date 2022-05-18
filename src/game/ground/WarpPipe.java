package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.PiranhaPlant;

public class WarpPipe extends Ground implements Jumpable{
    private int turnsActive = 0;
    private boolean piranhaPlantSpawned = false;
    /**
     * A constructor of the Warp Pipe class.
     */
    public WarpPipe() {
        super('C');
        this.addCapability(Status.WARP_POINT);

  }

  public String toString() {
    return "Warp Pipe";
  }

    @Override
    public void tick(Location location) {
        turnsActive++;
        if (turnsActive >= 1 && !piranhaPlantSpawned){
            location.addActor(new PiranhaPlant());
            piranhaPlantSpawned = true; //on reset, reset the boolean to false so plants can respawn
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
    public int getJumpSuccessRate() {
        return 100;
    }

    @Override
    public int getFallDamage() {
        return 0;
    }

    @Override
    public String getFlavourJump() {
        return "Off to another world? ";
    }

    @Override
    public String getFlavourFail() {
        return null;
    }

    @Override
    public String getFlavourDestroy() {
        return "Careful not to destroy all of them or it's game over man! ";
    }
}
