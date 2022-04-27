package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

public class TalkToToadAction extends Action {
  protected Actor target;

  public TalkToToadAction(Actor target) {
    this.target = target;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    return menuDescription(this.target);
  }

  @Override
  public String menuDescription(Actor actor) {
    ArrayList<String> monologues = new ArrayList<>();
    monologues.add("The Princess is depending on you! You are our only hope.");
    monologues.add("Being imprisoned in these walls can drive a fungus crazy :(");
    if (!actor.hasCapability(Status.HAS_WRENCH)){
      monologues.add("You might need a wrench to smash Koopa's hard shells.");
    }
    if (!actor.hasCapability(Status.TALL)){
      monologues.add("You better get back to finding the Power Stars.");
    }

    return monologues.get((int)(Math.random() * monologues.size()));
  }
}
