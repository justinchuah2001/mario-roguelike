package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.reset.Resettable;

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

  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(90, "chomps");
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    AttackAction attackAction = new AttackAction(this, direction);
    FireAttackAction fireAttackAction = new FireAttackAction(this,direction);

    // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)&& !otherActor.hasCapability(Status.SHOOTING_FIRE)) {
      actions.add(attackAction);
    }
    else if (otherActor.hasCapability(Status.SHOOTING_FIRE) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
      actions.add(fireAttackAction);

    }
    //behaviour that allows this actor to attack the other actor as a possible choice of action
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
    this.behaviours.put(1, new AttackBehaviour(otherActor, direction));
    } else{
      this.behaviours.remove(1);
    }

    return actions;
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    if (this.hasCapability(Status.RESET)){
      this.increaseMaxHp(50);
    }
    this.monologue.show(display);
    for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
      Action action = Behaviour.getAction(this, map);
      if (action != null)
        return action;
      }

    return new DoNothingAction();
  }

  @Override
  public void resetInstance() {
    this.addCapability(Status.RESET);
  }
}
