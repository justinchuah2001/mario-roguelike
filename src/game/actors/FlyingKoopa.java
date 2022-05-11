package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class FlyingKoopa extends Enemy{
    /**
     * Constructor.
     * Add behaviour that allows the actor to move around the map as a possible choice of action
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
