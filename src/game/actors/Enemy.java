package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviours.*;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that contains generic hostile character traits
 *
 * @author Justin Chuah, CaelanKao , Chan Jia Zheng
 * @version 1.1
 * @see game.actors.Goomba
 * @see game.actors.Koopa
 */
public abstract class Enemy extends Actor implements Resettable, Buffable {
  /**
   * A hash map that is used to store the possible behaviours for characters of this  class
   */
  protected final Map<Integer, Behaviour> behaviours = new HashMap<>();
  /**
   * Stuff that enemies says
   */
  protected Monologue monologue;
  /**
   * To track how many times this character's base attack was increased
   */
  private  int powerBuffCounter ;

  /**
   * Constructor.
   * Add behaviour that allows the actor to move around the map as a possible choice of action
   *
   * @param name        Name of the actor
   * @param displayChar Display character for actor that will be shown on map
   * @param hitPoints   Hit points the actor is supposed to have
   */
  public Enemy(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.behaviours.put(10, new WanderBehaviour());
    this.behaviours.put(9, new DrinkBehaviour());
    this.powerBuffCounter =0;
    this.registerInstance();
  }

  /**
   * This function returns a list of actions that other player is allowed to interact with the Enemy.
   *
   * @param otherActor The actor that might be performing the actions.
   * @param direction  String representing the direction of the other Actor
   * @param map        Current game map
   * @return A list of actions that other player is allowed to interact with the Enemy.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    AttackAction attackAction = new AttackAction(this, direction);
    FireAttackAction fireAttackAction = new FireAttackAction(this,direction);
    boolean inRange = false; //Updates the behaviour of the enemies when in ranage of player.

    // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)&& !otherActor.hasCapability(Status.SHOOTING_FIRE)) {
      actions.add(attackAction);
      inRange = true;
    }// If other actor, in this case player is under Fire Flower's effects, allows him to set fire to this actor!
    else if (otherActor.hasCapability(Status.SHOOTING_FIRE) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
        actions.add(fireAttackAction);
        inRange = true;
      }

    if (inRange){
      //behaviour that allows this actor to attack the other actor as a possible choice of action
      this.behaviours.put(2, new AttackBehaviour(otherActor, direction));
      //behaviour that allows this actor to follower the other actor as a possible choice of action
      this.behaviours.put(3, new FollowBehaviour(otherActor));
    }
    return actions;
  }

  /**
   * Add the RESET Status to coin, it will be removed if it is ticked
   */
  @Override
  public void resetInstance() {
    this.addCapability(Status.RESET);
  }

  /**
   * Gets the number of times a character was buffed.
   * @return Number of times a character was buffed.
   */
  @Override
  public int getCounter() {
    return powerBuffCounter;
  }

  /**
   * Increase the buff counter by 1 if under the effects of power water.
   */
  @Override
  public void increaseCounter() {
    this.removeCapability(Status.POWER_UP);
    powerBuffCounter += 1;
  }

}
