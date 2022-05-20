package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Fountain;

public class DrinkBehaviour implements Behaviour {

    public DrinkBehaviour(Fountain fountain) {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new DoNothingAction();
    }
}
