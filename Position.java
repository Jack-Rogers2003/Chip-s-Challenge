import java.util.HashMap;

/**
 * Class that is used to track the positions of objects on the board
 * @author Jack Rogers
 * @version 1.0
 */

public class Position {
    private static Integer[] playerPosition;
    private static HashMap<Object, Integer[]> monsterPosition;
    private static HashMap<Item, Integer[]> itemPosition;
    private static Integer[] blockPosition;
    private static HashMap<Integer[], Tile> tilePosition;

    /**
     * Returns the position of the player on the board
     * @return The player's position as a Pair of Integers
     * representing the x and y coordinates
     */
    public Integer[] getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Sets the new location of the player on the board
     * as a pair of it's x and y position
     * @param newPlayerPosition The new player position
     */
    public void setPlayerPosition(Integer[] newPlayerPosition) {
        playerPosition = newPlayerPosition;
    }

    /**
     * Returns the position of a monster
     * @param monster the monster we are looking for the position of
     * @return The position of a monster as a Pair
     * of integers of its x and y position
     */
    public Integer[] getMonsterPosition(Object monster) {
        return monsterPosition.get(monster);
    }

    /**
     * Sets the new position of a monster after a movement
     * @param monster The monster that's position is changing
     * @param newPosition The new position of the monster
     */
    public void setMonsterPosition(Object monster, Integer[] newPosition) {
        monsterPosition.put(monster, newPosition);
    }

    /**
     * Returns the position of an item on the board
     * @param itemToFind Item we are looking for the position of
     * @return the position of the item as an array of Integers representing
     * the x and y coordinates
     */
    public Integer[] getItemPosition(Item itemToFind) {
        return itemPosition.get(itemToFind);
    }

    /**
     * This method removes an item from the HashMap of itemPosition
     * The reason it is removed rather than simply moved is that once an item
     * is in the player's inventory, it is no longer on the actual board itself
     * therefore we remove it entirely from here, though the object itself
     * still exists within the player's inventory
     * @param itemToRemove The item we are removing from the board
     */
    public void removeItem(Item itemToRemove) {
        itemPosition.remove(itemToRemove);
    }

    /**
    * Returns the current position of the block on the board
    * @return the positon of block as an Array of Integers containing its
    * X and Y coordinate
    */
    public Integer[] getBlockPosition() {
        return blockPosition;
    }

    /**
    * Sets the new position of the block after it has been moved
    * @param newPosition the new position of the block as an Array of Integers
    * representing its X and Y coordinate 
    */
    public void setBlockPosition(Integer[] newPosition) {
        blockPosition = newPosition;
    }
}
