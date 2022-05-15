package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.Status;
import game.actions.SavePrincessAction;

public class PrincessPeach extends Actor {
  private Monologue monologue;

  private final static String[] sentences = {"Dear Mario, I'll be waiting for you...",
          "Never gonna give you up!",
          "Release me, or I will kick you!"};

  /**
   * Constructor for Princess Peach
   */
  public PrincessPeach() {
    super("Princess Peach", 'P', 100000);
    this.monologue = new Monologue(this, sentences);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HAS_KEY)) {
      actions.add(new SavePrincessAction(this));

    }
    return actions;
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    return new DoNothingAction();
  }
}
