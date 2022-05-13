package game.ground;

import edu.monash.fit2099.engine.actors.Actor;

public class PowerFountain extends Fountain{
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public PowerFountain(char displayChar) {
        super('A');
    }

    @Override
    public void drinkEffects(Actor actor) {
        //actor.addcapability(power???)
    }
}
