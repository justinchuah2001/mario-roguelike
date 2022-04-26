package game.items;

public class SuperMushroom extends Consumables{
    private final int  price;
    public SuperMushroom(){
        super("Super Mushroom", '^', true);
        this.price= 400;
    }

    public int getPrice() {
        return price;
    }
}
