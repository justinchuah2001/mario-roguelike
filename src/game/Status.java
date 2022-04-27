package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    DORMANT, // use this status to show that the Koopa has now entered dormant state.
    HAS_WRENCH, // use this status to show that the Player now possess the wrench in his inventory.
    BUY_FROM_TOAD,
    TALK_TO_TOAD,
    PRE_DORMANT,
    INVINCIBLE,
    RESET
}
