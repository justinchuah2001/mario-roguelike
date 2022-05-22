package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status.PermanentStatus;
import game.Status.TempStatus;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviours.AttackBehaviour;
import game.reset.Resettable;

/**
 * A plant that is somehow always angry towards the Player, how does it live in the Pipe?
 *
 * @author Justin Chuah, Jia Zheng
 * @version 1.0
 * @see game.ground.WarpPipe
 */
public class PiranhaPlant extends Enemy implements Resettable {
  /**
   * Sentences said by PiranhaPlant.
   */
  private final static String[] sentences = {"Slsstssthshs~! (Never gonna say goodbye~)",
          "Gaga, ooh-la-la!",
          "Want your bad romance!"};

  /**
   * Constructor for Piranha Plant.
   * Add behaviour that allows the actor to move around the map as a possible choice of action
   */
  public PiranhaPlant() {
    super("Piranha Plant", 'Y', 150);
    this.behaviours.clear();
    this.monologue = new Monologue(this, sentences);
    this.resetInstance();
  }

  /**
   * Weapon of choice for a PiranhaPlant, it uses its sharp teeth!
   *
   * @return Weapon used to damage other actors(Player).
   */
  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(90, "chomps");
  }

  /**
   * Determine what actions can be taken onto the piranha plant.
   *
   * @param otherActor The actor that might be performing the actions.
   * @param direction  String representing the direction of the other Actor
   * @param map        Current game map
   * @return Actions that the other player can take on this.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    AttackAction attackAction = new AttackAction(this, direction);
    FireAttackAction fireAttackAction = new FireAttackAction(this, direction);

    // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
    if (otherActor.hasCapability(PermanentStatus.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(TempStatus.SHOOTING_FIRE)) {
      actions.add(attackAction);
    } // It allows the player to set this actor on fire if he is under the efffects of Fire Flower.
    else if (otherActor.hasCapability(TempStatus.SHOOTING_FIRE) && otherActor.hasCapability(PermanentStatus.HOSTILE_TO_ENEMY)) {
      actions.add(fireAttackAction);

    }
    //Behaviour that only allows this actor to attack the player.
    if (otherActor.hasCapability(PermanentStatus.HOSTILE_TO_ENEMY)) {
      this.behaviours.put(1, new AttackBehaviour(otherActor, direction));
    } else {
      this.behaviours.remove(1);
    }

    return actions;
  }

  /**
   * Determine what action to take.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return Action according to the list of behaviour if valid, otherwise does nothing!
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    if (this.hasCapability(PermanentStatus.RESET)) { // This actor only gets stronger after resetting the game instance.
      this.increaseMaxHp(50);
    }
    for (game.behaviours.Behaviour Behaviour : this.behaviours.values()) {
      Action action = Behaviour.getAction(this, map);
      if (action != null)
        return action;
    }

    return new DoNothingAction();
  }

  /**
   * Notifies the game to reset this char.
   */
  @Override
  public void resetInstance() {
    this.addCapability(PermanentStatus.RESET);
  }
}
