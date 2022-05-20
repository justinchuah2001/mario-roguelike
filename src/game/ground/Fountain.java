package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FillBottleAction;
import game.items.Water;

public abstract class Fountain extends Ground {
  private int availableWater;
  private int refillTimer;
  private boolean isDepleted;
  private Water water;

  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public Fountain(char displayChar, Water water) {
    super(displayChar);
    this.isDepleted = false;
    this.availableWater = 10;
    this.water = water;
    this.addCapability(Status.ON_FOUNTAIN);
  }

  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) &&  (location.containsAnActor())) {
      actions.add(new FillBottleAction());
    }
    return actions;
  }

}
