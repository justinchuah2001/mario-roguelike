package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.BreakShellAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

/**
 * A little turtle who is hostile to the player when in range.
 *
 * @author Justin Chuah
 * @version 1.0
 */
public abstract class Koopa extends Enemy {
  /**
   * Sentences said by Koopa.
   */
  private final static String[] sentences = {"Never gonna make you cry!",
          "Koopi koopi koopii~!"};

  /**
   * Constructor
   * Add SuperMushroom to its inventory that gets dropped on death
   * Given PRE_DORMANT status to allow Koopa to enter dormant when not conscious
   */
  public Koopa(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.behaviours.put(10, new WanderBehaviour());
    this.addItemToInventory(new SuperMushroom());
    this.addCapability(Status.PRE_DORMANT);
    this.monologue = new Monologue(this, sentences);
  }

  /**
   * Method for which the Koopa attacks another actor
   *
   * @return damage value of his attacks and the key phrase for it
   */
  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(30, "punches");
  }

  /**
   * This function returns a list of actions that other player is allowed to interact with the Koopa.
   *
   * @param otherActor The actor that might be performing the actions.
   * @param direction  String representing the direction of the other Actor
   * @param map        Current game map
   * @return A list of actions that other player is allowed to interact with the Koopa.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    // If Koopa is currently in dormant state(in shell ) and other actor wields a wrench,
    // allow the other actor to destroy the shell which drops a mushroom at the location
    if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HAS_WRENCH)) {
      actions.add(new BreakShellAction(this, direction));
      this.behaviours.clear(); //Koopa in dormant state is not allowed to move or attack
    } else if (this.hasCapability(Status.DORMANT)) {
      actions.clear(); ////Koopa in dormant state is not allowed to move or attack
    } else {
      actions.add(super.allowableActions(otherActor, direction, map));
    }
    return actions;
  }

  /**
   * This function returns what Koopa should do during its turn.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return DoNothingAction() if Koopa is in dormant state, since it is not able to do anything, otherwise standard action list
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    //If user chooses to reset game, remove from map
    if (this.hasCapability(Status.RESET)) {
      map.removeActor(this);
    } else if (this.hasCapability(Status.DORMANT)) { //If status of koopa is dormant, can no longer be pre-dormant
      this.removeCapability(Status.PRE_DORMANT);
      this.setDisplayChar('D');
      actions.clear(); //Dormant koopa is not allowed to move or attack
    } else {
      ////Return the action for this actor at end of the turn
      for (Behaviour Behaviour : behaviours.values()) {
        Action action = Behaviour.getAction(this, map);
        if (action != null)
          return action;
      }
    }
    this.monologue.show(display);
    return new DoNothingAction();
  }
}
