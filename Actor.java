/**
 * Class representing actors in the game, such as players, monsters (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 *
 * @author Benji Brew, Uzair
 * @version 1.1 
 * Changed the class from abstract to concrete. Added new method addMoveableObeject which is supposed to help with tracking the moveable objects 
 *(I'm not sure whether it will work like I intended so if anyone knows how to better implement a method for such behaiviour would be appreciated)
 */
public class Actor {

    private Position position;
    private int gameTickMovementInterval;
    private List<Actor> moveableObjects; 

    /**
     * Constructor for creating a new Actor.
     *
     * @param position The starting position of the actor on the board.
     * @param gameTickMovementInterval Interval at which the actor moves in terms of game ticks.
     */
    public Actor(Position position, int gameTickMovementInterval) {
        this.position = position;
        this.gameTickMovementInterval = gameTickMovementInterval;
        this.moveableObjects = new ArrayList<>();
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
     * Adds a movable object to the actor's list of moveable objects
     *
     * @param moveableObject The moveable object (Player, Monster, Block)
     */
    public void addMoveableObjects(Actor moveableObject) {
        moveableObjects.add(moveableObject);
    

    /**
     * Performs an action specific to the type of actor.
     * This can be movement, interaction with objects, or other game mechanics.
     *
     * @param currentGameTick The current game tick.
     */
        public void performAction(int currentGameTick) {
        for (Actor moveableObject : moveableObjects) {
            moveableObject.performAction(currentGameTick);
        }
    }
        

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
