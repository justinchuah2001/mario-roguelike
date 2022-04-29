package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Jumpable;

public class JumpAction extends Action {
    private Jumpable jumpable;
    private Location jumpableLocation;
    private String direction;

    public JumpAction(Jumpable jumpable, Location jumpableLocation, String direction){
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return jumpable.jump(actor, jumpableLocation);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps onto the " + jumpable.toString() + "(" + jumpableLocation.x() + ", " + jumpableLocation.y() +  ") to the " + direction;
    }
}
