package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.HashMap;
import java.util.Map;

public class Koompa extends Enemy{
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Koompa(){
        super("Koompa", 'K', 100);
        addItemToInventory(new SuperMushroom());
        this.behaviours.put(1, new WanderBehaviour());
    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HASWRENCH)){
                actions.add(new DestroyShellAction(this, direction));
            }
            else{
            actions.add(new AttackAction(this,direction));
            this.behaviours.put(2, new AttackBehaviour(otherActor));
            this.behaviours.put(3, new FollowBehaviour(otherActor));
            }
        }
        return actions;
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.DORMANT)){
            this.setDisplayChar('D');
            return new DoNothingAction();
        }
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
