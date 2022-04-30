package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumables;
import game.items.SuperMushroom;

public class ConsumeItemAction extends Action {
    protected Consumables consumedItem;


    public ConsumeItemAction(Consumables consumedItem){
        this.consumedItem = consumedItem;
    }



    @Override
    public String execute(Actor self, GameMap map) {

        for (Item item : self.getInventory())
            if (consumedItem == item){
                this.consumedItem.consumedEffect(self);
                self.removeItemFromInventory(item);

                return self + " has consumed " + consumedItem;
        }

        this.consumedItem.consumedEffect(self);
        map.locationOf(self).removeItem(consumedItem);
        return self + " has consumed " + consumedItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + consumedItem;
    }

}