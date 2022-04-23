package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumables;

public class ConsumeItemAction extends Action {
    protected Actor self;
    protected Consumables consumedItem;

    public ConsumeItemAction(Actor self, Consumables consumedItem){
        this.self= self;
        this.consumedItem = consumedItem;

    }

    @Override
    public String execute(Actor self, GameMap map) {

        for (Item item : self.getInventory())
            if (consumedItem == item){
                self.getInventory().remove(item);
                return self + "has consumed " + consumedItem;

        }

        return "Item does not exist in inventory!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "has consumed" + consumedItem;
    }

}
