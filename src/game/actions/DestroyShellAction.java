package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class DestroyShellAction extends Action {
    protected Actor target;
    protected String direction;

    public DestroyShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {


        if (target.hasCapability(Status.DORMANT)) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            return actor + "has destroyed " + target +"shell.";
        }

        return "Can only destroy Koopa shell!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "destroyed shell at" + direction;
    }
}
