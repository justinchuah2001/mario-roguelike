package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public abstract class Fountain extends Ground {
    private int availableWater;
    private int refillTimer;
    private boolean isDepleted;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        this.isDepleted = false;
        this.availableWater = 10;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY) && availableWater>=5){
            //this.addAction(new drinkWaterAction(this));
        }
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && availableWater>=1){
            //this.addAction(new fillBottleAction(tis));
        }
        return null;
    }

    public abstract void drinkEffects(Actor actor);
}
