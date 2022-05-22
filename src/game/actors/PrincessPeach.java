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

/**
 * The princess who was trapped and is now being kidnapped by Bowser. Save her! .... or not.
 */
public class PrincessPeach extends Actor {
  /**
   * Monologue of PrincessPeach.
   */
  private Monologue monologue;
  /**
   * Sentences said by PrincessPeach.
   */
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

  /**
   * Actions that can be taken on by the player to this actor
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return Possible actions that can be acted upon this actor.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HAS_KEY)) { // If player gets the key from Bowser, ends the game!
      actions.add(new SavePrincessAction(this));

    }
    return actions;
  }

  /**
   * Determine what action to take for the turn
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return Princess can't do anything as she is trapped, so does nothing!
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    return new DoNothingAction();
  }
}
