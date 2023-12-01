/**
 * Class representing the player in the game.
 * The player has specific behaviors like moving based on keyboard input and interacting with game elements.
 * @author Benji Brew, Uzair
 * @version 1.1
 * Added methods to show items held by player and ways to add and remove them as well 
 */
public class Player extends Actor {

    private String playerName;
    private int highestLevelUnlocked;
    private boolean onIce;
    private List<Item> items;

    /**
     * Constructor for creating a new Player.
     *
     * @param position The starting position of the player on the board.
     * @param playerName The name of the player.
     */
    public Player(Position position, String playerName) {
        super(position, 3); // Assuming the player moves every 3 game ticks
        this.playerName = playerName;
        this.highestLevelUnlocked = 1; // Default starting level
        this.onIce = false;
        this.items = new ArrayList<>();
    }

   /**
    * Gets the player's items.
    *
    * @return The list of items the player has.
    */
    public List<Item> getItems() {
        return items;
    }
    
    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Removes an item from the player's inventory.
     *
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name.
     *
     * @param playerName The new name of the player.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the highest level unlocked by the player.
     *
     * @return The highest level unlocked.
     */
    public int getHighestLevelUnlocked() {
        return highestLevelUnlocked;
    }

    /**
     * Sets the highest level unlocked by the player.
     *
     * @param highestLevelUnlocked The new highest level unlocked.
     */
    public void setHighestLevelUnlocked(int highestLevelUnlocked) {
        this.highestLevelUnlocked = highestLevelUnlocked;
    }

    /**
     * Determines whether the player can move to the given position.
     *
     * @param newPosition The position to be moved to.
     * @param tileLayer The current state of the tile layer.
     * @param actorLayer The current state of the actor layer.
     * @return true if the player can move to the newPosition, false otherwise.
     */
    @Override
    public void moveTo(Position newPosition, Tile[][] tileLayer, Actor[][] actorLayer) {
        //canMoveTo represents a boolean method in Movement.java
        //Ask Jack R to see what he has done in Movement.java  
        if (canMoveTo(newPosition, tileLayer, actorLayer)) {
            setPosition(newPosition);
        }
    }

    /**
     * Performs an action specific to the player, like moving based on keyboard input.
     *
     * @param currentGameTick The current game tick.
     */
    @Override
    public void performAction(int currentGameTick) {
        // Implement player-specific actions like moving based on keyboard input
        // This method would be called at each game tick
        // Again will ask Jack R about this in meeting
    }

    /**
     * Updates the player's state when moving onto an ice tile.
     *
     * @param onIce Indicates whether the player is currently on an ice tile.
     */
    public void setOnIce(boolean onIce) {
        this.onIce = onIce;
    }

    /**
     * Checks if the player is currently on an ice tile.
     *
     * @return true if the player is on an ice tile, false otherwise.
     */
    public boolean isOnIce() {
        return onIce;
    }
}

