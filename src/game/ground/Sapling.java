package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

public class Sapling extends Tree {


    /**
     * Constructor.
     *
     */
    public Sapling(){
        super('t');
        this.age = 10;
    }

    public String toString(){
        return "Sapling";
    }

    @Override
    public String jump(Actor actor, Location location) {
        if(actor.hasCapability(Status.INVINCIBLE)){
            String destroyMessage = actor + " destroys the " + location.getGround().toString() + "! Maybe that one was worth keeping around? A coin appeared!";

            location.map().moveActor(actor, location);
            location.setGround(new Dirt());
            location.addItem(new Coin(5));

            return destroyMessage;
        }
        else if(actor.hasCapability(Status.TALL)){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + " with no problem! Wahoo!";
        } else if(r.nextInt(10) <= 7){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + "! Wahoo!";
        }else{
            actor.hurt(20);
            return  actor + " fails to jump the " + location.getGround().toString() +". Took 20 fall damage. Owie!";
        }
    }


    @Override
    public void tick(Location location){
        super.tick(location);

        if(r.nextInt(10) == 0){
            location.addItem(new Coin(20));
        }
    }
}
