package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;

import java.util.Random;

/**
 * A little fungus guy who is hostile to the player when in range.
 *
 * @author Justin Chuah
 * @version 1.0
 */
public class Goomba extends Enemy {
  private final static String[] sentences = {"Rah, rah-ah-ah-ah!",
          "Ugha ugha... (Never gonna run around and desert you...)",
          "Roma, roma-ma!"};
  /**
   * random number generator
   */
  private final Random randomInt = new Random();

  /**
   * Constructor.
   */
  public Goomba() {
    super("Goomba", 'g', 50);
    this.monologue = new Monologue(this, sentences);
  }

  /**
   * Deletes the Goomba from map at a 10% chance
   *
   * @return true if random integer generate is equal to SUICIDE constant int value
   */
  public boolean suicide() {
    final int SUICIDE = 1;
    return (randomInt.nextInt(10)) <= SUICIDE;
  }

  /**
   * Method for which the Goomba attacks another actor
   *
   * @return damage value of his attacks and the key phrase for it
   */
  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    int baseDamage = 10;
    return new IntrinsicWeapon(baseDamage + getAttackIncrease(),"kicks");
  }

  /**
   * Figure out what to do next.
   *
   * @see Actor#playTurn(ActionList, Action, GameMap, Display)
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    // If the 10% chance for suicide occurs or reset is inputted by user, remove from map
    if (suicide() || this.hasCapability(Status.RESET)) {
      map.removeActor(this);
      return new DoNothingAction();
    }
    if (this.hasCapability(Status.POWER_UP)){
      increaseCounter();
    }
    //Return the action for this actor at end of the turn
    for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
      Action action = Behaviour.getAction(this, map);
      if (action != null)
        return action;
    }
    this.monologue.show(display);
    return new DoNothingAction();
  }

}

