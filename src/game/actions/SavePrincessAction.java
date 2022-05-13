package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SavePrincessAction extends Action {
    protected Actor target;

    public SavePrincessAction(Actor target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "You have successfully saved the princess, congratulations!!!!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves the princess?";
    }
}
