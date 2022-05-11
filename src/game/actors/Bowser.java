package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Bowser extends Enemy{
    /**
     * Constructor for Bowser
     * Add behaviour that allows the actor to move around the map as a possible choice of action
     */
    public Bowser() {
        super("Bowser", 'B', 500);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
