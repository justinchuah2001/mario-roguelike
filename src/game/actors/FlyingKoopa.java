package game.actors;


import game.Status;

public class FlyingKoopa extends Koopa {
    /**
     * Constructor.
     * Add behaviour that allows the actor to move around the map as a possible choice of action
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLYING);
        this.monologue.addSentence("Pam pam pam!");
    }
}
