package game.ground;

public interface Buffable {
    default int getAttackIncrease(){
        return getCounter()*15;
    }
    int increaseCounter();
    int getCounter();

}
