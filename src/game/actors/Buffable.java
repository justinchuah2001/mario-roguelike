package game.actors;

/**
 * Buffable interface os applied on actors that can come under the effects that increases their damage.
 * @author Justin Chuah
 * @version 1.0
 */
public interface Buffable {
    /**
     * Get the total amount of attack increased for the actor.
     * @return Value of total buffed attack.
     */
    default int getAttackIncrease(){
        return getCounter()*15;
    }

    /**
     * Method of increasing counter of times buffed
     */
    void increaseCounter();

    /**
     *
     * @return Number of times a character was buffed.
     */
    int getCounter();

}
