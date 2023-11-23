/**
 * Class representing actors in the game, such as players, monsters (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 *
 * @author Benji Brew
 * @version 1.0
 */
public abstract class Actor {

    private Position position;
    private int gameTickMovementInterval;

    /**
     * Constructor for creating a new Actor.
     *
     * @param position The starting position of the actor on the board.
     * @param gameTickMovementInterval Interval at which the actor moves in terms of game ticks.
     */
    public Actor(Position position, int gameTickMovementInterval) {
        this.position = position;
        this.gameTickMovementInterval = gameTickMovementInterval;
    }

    /**
     * Gets the current position of the actor.
     *
     * @return The current position of the actor.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of the actor.
     *
     * @param position The new position of the actor.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Moves the actor to a new position if possible.
     * @param newPosition The position to be moved to.
     * @param tileLayer The current state of the tile layer.
     * @param actorLayer The current state of the actor layer.
     */
    public void moveTo(Position newPosition, Tile[][] tileLayer, Actor[][] actorLayer) {
        if (canMoveTo(newPosition, tileLayer, actorLayer)) {
            setPosition(newPosition);
        }
    }

    /**
     * Performs an action specific to the type of actor.
     * This can be movement, interaction with objects, or other game mechanics.
     *
     * @param currentGameTick The current game tick.
     */
    public abstract void performAction(int currentGameTick);

    /**
     * Determines if the actor should move based on the current game tick.
     *
     * @param currentGameTick The current game tick.
     * @return true if the actor should move on this tick, false otherwise.
     */
    public boolean shouldMove(int currentGameTick) {
        return currentGameTick % gameTickMovementInterval == 0;
    }
}
