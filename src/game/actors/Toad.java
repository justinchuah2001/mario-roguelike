package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.Status;
import game.items.Wrench;
import game.actions.BuyItemAction;
import game.actions.TalkToToadAction;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * This class represents the actor Toad in the game.
 *
 * @author Chan Jia Zheng
 * @version 1.0
 */
public class Toad extends Actor {
  private Monologue monologue;

  private final static String[] sentences = {"You might need a wrench to smash Koopa's hard shells.",
          "You better get back to finding the Power Stars.",
          "The Princess is depending on you! You are our only hope.",
          "Being imprisoned in these walls can drive a fungus crazy :("};

  /**
   * Constructor.
   * Add item to its inventory.
   */
  public Toad() {
    super("Toad", 'O', 1);
    this.monologue = new Monologue(this, sentences);
  }

  /**
   * This function returns a list of actions that other player is allowed to interact with the Toad.
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return A list of actions that other player is allowed to interact with the Toad.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    if (otherActor.hasCapability(Status.TALK_TO_TOAD)) {           // it can be talked to only by the TALK_TO_TOAD actor
      actions.add(new TalkToToadAction(otherActor));
    }
    if (otherActor.hasCapability(Status.BUY_FROM_TOAD)) {   // only TALK_TO_TOAD actor can buy from the Toad
      actions.add(BuyItemAction.getInstance(new SuperMushroom()));
      actions.add(BuyItemAction.getInstance(new PowerStar()));
      if (!otherActor.hasCapability(Status.HAS_WRENCH)) {
        actions.add(BuyItemAction.getInstance(new Wrench()));
      }
    }
    return actions;
  }

  /**
   * This function returns what should the Toad do during its turn.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return The Toad do nothing, so DoNothingAction()
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    return new DoNothingAction();
  }
}
