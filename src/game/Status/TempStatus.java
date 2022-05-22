package game.Status;

public enum TempStatus {
    TALL,             // use this status to tell that current instance has "grown".
    INVINCIBLE,       // use this status to show that the Player is now under the effects of PowerStar
    SHOOTING_FIRE,    // use this status to show that the actor's attack spawns fire
    IS_DEPLETED,      // use this status to show that the fountain's water has been depleted
    WAS_COLLECTED,    // use this status to show that the fountain's was collected by the player using a bottle
    DRANK_FROM,        // use this status to show that an actor aside from Player has drank the fountain's water
    POWER_UP,         // use this status to show that the actor is under the effects of Power Water
    ON_FOUNTAIN      // use this status to show that the actor is currently on a fountain
}
