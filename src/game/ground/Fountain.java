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
  private boolean isDepleted;

  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public Fountain(char displayChar) {
    super(displayChar);
    this.isDepleted = false;
    this.availableWater = 10;
    this.refillTimer = 5;
    this.addCapability(Status.ON_FOUNTAIN);
  }

  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) &&  (location.containsAnActor()) &&(!this.isDepleted)) {
      actions.add(new FillBottleAction());
    }
    return actions;
  }

  @Override
  public void tick(Location location) {
    if (this.isDepleted && this.refillTimer>0){
      this.refillTimer-=1;
      if (this.refillTimer==0){
        this.isDepleted = false;
        refreshedFountain();
      }
    } else if (!this.isDepleted && this.hasCapability(Status.WAS_COLLECTED)){
      this.availableWater -= 1;
      if (this.availableWater==0){
        this.isDepleted = true;
      }
    }
  }
  public void refreshedFountain(){
    this.refillTimer = 5;
    this.availableWater = 10;
  }
}
