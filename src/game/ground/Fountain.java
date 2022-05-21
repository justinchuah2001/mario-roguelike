package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FillBottleAction;


public abstract class Fountain extends Ground {
  private int availableWater;
  private int refillTimer;

  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public Fountain(char displayChar) {
    super(displayChar);
    this.availableWater = 10;
    this.refillTimer = 5;
    this.addCapability(Status.ON_FOUNTAIN);
  }

  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    if (actor.hasCapability(Status.HAS_BOTTLE) &&  (location.containsAnActor()) &&(!this.hasCapability(Status.IS_DEPLETED))) {
      actions.add(new FillBottleAction(location,this.availableWater));
    }
    return actions;
  }

  @Override
  public void tick(Location location) {
    if (this.hasCapability(Status.IS_DEPLETED) && this.refillTimer>0){
      this.refillTimer-=1;
      if (this.refillTimer==0){
        this.removeCapability(Status.IS_DEPLETED);
        refreshedFountain();
      }
    } else if (!this.hasCapability(Status.IS_DEPLETED)){
      if (this.hasCapability(Status.WAS_COLLECTED)){
        this.availableWater -= 1;
        this.removeCapability(Status.WAS_COLLECTED);
      } else if (this.hasCapability(Status.DRANK_FROM)){
        this.availableWater -= 1;
        this.removeCapability(Status.DRANK_FROM);
      }
      if (this.availableWater==0){
        this.addCapability(Status.IS_DEPLETED);
      }
    }
  }
  public void refreshedFountain(){
    this.refillTimer = 5;
    this.availableWater = 10;
  }


}
