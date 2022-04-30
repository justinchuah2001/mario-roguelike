package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.AttackAction;
import game.actions.DestroyShellAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

public class Koopa extends Enemy {
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Koopa(){
        super("Koopa", 'K', 100);
        addItemToInventory(new SuperMushroom());
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.PRE_DORMANT);

    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HAS_WRENCH)){
            actions.add(new DestroyShellAction(this, direction));
            this.behaviours.clear();
            }else if (this.hasCapability(Status.DORMANT)){
            actions.clear();
            }
            else{
            actions.add(super.allowableActions(otherActor, direction, map));
            }
        return actions;
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)) {
            map.removeActor(this);
        } else if (this.hasCapability(Status.DORMANT)){
            this.removeCapability(Status.PRE_DORMANT);
            this.setDisplayChar('D');
            actions.clear();
        } else {
            for (Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }
}
