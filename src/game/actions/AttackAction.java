package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * Special Action for attacking other Actors.
 *
 * @author Justin Chuah
 * @version 1.1
 */
public class AttackAction extends Action {

  /**
   * The Actor that is to be attacked
   */
  protected Actor target;

  /**
   * The direction of incoming attack.
   */
  protected String direction;

  /**
   * Random number generator
   */
  protected Random rand = new Random();

  /**
   * Constructor.
   *
   * @param target    the Actor to attack
   * @param direction The direction of incoming attack.
   */
  public AttackAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  /**
   * This function let an Actor attack another actor(target)
   *
   * @param actor The Actor who does the attack onto the target
   * @param map   The map the actor is on.
   * @return A description whether the actor successfully attacks the target
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    Weapon weapon = actor.getWeapon();

    if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
      return actor + " misses " + target + ".";
    }

    int damage;
    if (actor.hasCapability(Status.FINAL_BOSS)) { // If actor is Bowser, sets fire on floor of target
      map.locationOf(target).addItem(new Fire());
    }

    if (target.hasCapability(Status.INVINCIBLE)) { //If target of attack action is invincible, deal no damage
      damage = 0;
      target.hurt(damage);

    } else if (actor.hasCapability(Status.INVINCIBLE)) { //If current person attack is invincible, kill enemy target instantly
      damage = 999999;
      target.hurt(damage);
    } else {
      damage = weapon.damage();
      target.hurt(damage);
    }
    String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
    if (target.hasCapability(Status.TALL)) { //If actor with tall status is damaged, remove tall status
      target.removeCapability(Status.TALL);
    }
    if (!target.isConscious()) {
      ActionList dropActions = new ActionList();
      // drop all items
      for (Item item : target.getInventory())
        dropActions.add(item.getDropAction(actor));
      for (Action drop : dropActions)
        drop.execute(target, map);
      // remove actor if he does not possess PRE_DORMANT status
      if (!target.hasCapability(Status.PRE_DORMANT)) {
        map.removeActor(target);
        if (target instanceof Resettable) {
          ResetManager.getInstance().cleanUp((Resettable) target);
        }
      } else {
        target.addCapability(Status.DORMANT); //Give actor with PRE_DORMANT status DORMANT status if unconscious
      }
      result += System.lineSeparator() + target + " is killed.";
    }

    return result;
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor Actor who does the attacking
   * @return Menu description of the action.
   */

  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks " + target + " at " + direction;
  }
}
