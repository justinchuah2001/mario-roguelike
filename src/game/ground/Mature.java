package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Koopa;
import game.items.Coin;

public class Mature extends Tree {

    /**
     * Constructor.
     */
    public Mature() {
        super('T');
        this.age = 0;
    }

    public String toString(){
        return "Mature Tree";
    }

    @Override
    public String jump(Actor actor, Location location) {
        if(actor.hasCapability(Status.INVINCIBLE)){

            String destroyMessage = actor + " destroys the " + location.getGround().toString() + "! No more Koopas from you! A coin appeared!";

            location.map().moveActor(actor, location);
            location.setGround(new Dirt());
            location.addItem(new Coin(5));

            return destroyMessage;

        }else if(actor.hasCapability(Status.TALL)){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + " with no problem! Yahoohoo!~";
        } else if(r.nextInt(10) <= 6){
            location.map().moveActor(actor, location);
            return actor + " jumps up the " + location.getGround().toString() + "! Yahoohoo!~";
        }else{
            actor.hurt(30);
            return  actor + " fails to jump the " + location.getGround().toString() +". Took 30 fall damage. Ouch!";
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (r.nextInt(5) == 0) {
            location.setGround(new Dirt());
            return;
        }

        if (r.nextInt(20) < 3 && !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
        if (age == 5 && hasFertileGround(location)) {
            spawnSprout(location);
            this.age = 0;
        }
    }

    private void spawnSprout(Location location) {
        int x = 0;
        int y = 0;
        do {
            switch (r.nextInt(8)) {
                case 0 -> {
                    x = location.x() - 1;
                    y = location.y() - 1;
                }
                case 1 -> y = location.y() - 1;
                case 2 -> {
                    x = location.x() + 1;
                    y = location.y() - 1;
                }
                case 3 -> x = location.x() - 1;
                case 4 -> x = location.x() + 1;
                case 5 -> {
                    x = location.x() - 1;
                    y = location.y() + 1;
                }
                case 6 -> y = location.y() + 1;
                case 7 -> {
                    x = location.x() + 1;
                    y = location.y() + 1;
                }
            }
        } while (!location.map().getXRange().contains(x) || !location.map().getYRange().contains(y) || !(location.map().at(x, y).getGround() instanceof Dirt));

        location.map().at(x, y).setGround(new Sprout());
    }

    private boolean hasFertileGround(Location location) {
        for(int x = location.x() - 1; x < location.x() + 2; x++){
            for(int y = location.y() - 1; y < location.y() + 2; y++){
                if(location.map().getXRange().contains(x) && location.map().getYRange().contains(y) &&
                        location.map().at(x,y).getGround() instanceof Dirt){
                    return true;
                }
            }
        }
        return false;
    }
}


