package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Water;

import java.util.ArrayList;

public class DrinkBottleAction extends Action {
    private ArrayList<Water> bottleContent;

    public DrinkBottleAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        bottleContent = Bottle.getInstance().getBottleContent();
        if (!bottleContent.isEmpty()){
        Water water = bottleContent.get(bottleContent.size()-1);
        water.waterEffect(actor);
        bottleContent.remove(bottleContent.size()-1);
        }

        return actor + " drank the water in the bottle! ";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drink water in the bottle. " + Bottle.getInstance();
    }

}
