package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that contains generic hostile character traits
 *
 * @see game.actors.Goomba
 * @see game.actors.Koopa
 */
public abstract class Enemy extends Actor implements Resettable {
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
            this.behaviours.put(2, new AttackBehaviour(otherActor, direction));
            this.behaviours.put(3, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
