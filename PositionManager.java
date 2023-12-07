import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class that is used to track the positions of objects on the board
 * @author Jack Rogers
 * @version 1.0
 */

public class PositionManager {
    private static int[] playerPosition;
    private static HashMap <Object, int[]> monsterPosition = new HashMap<>();
    private static HashMap <Item, int[]> itemPosition = new HashMap<>();
    private static HashMap <Block, int[]> blockPosition = new HashMap<>();
    private static ArrayList<ArrayList<Tile>> tilePosition = new ArrayList<>();

    /**
     * Used for when first generating the game, sets all attributes to empty,
     * this is to prevent possible bugs or issues when transferring from one
     * level to another, such as a player's collected items carrying over
     */
    public static void resetPositions() {
        tilePosition.clear();
        blockPosition.clear();
        itemPosition.clear();
        monsterPosition.clear();
    }

    /**
     * Returns the position of the player on the board
     * @return The player's position as a Pair of Integers
     * representing the x and y coordinates
     */
    public static int[] getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Sets the new location of the player on the board
     *      * as a pair of it's x and y position
     * @param newPlayerPosition the new x coordinate of the player
     */
    public static void setPlayerPosition(int[] newPlayerPosition) {
        playerPosition = newPlayerPosition;
    }

    /**
     * Returns the position of a monster
     * @param monster the monster we are looking for the position of
     * @return The position of a monster as a Pair
     * of integers of its x and y position
     */
    public static int[] getMonsterPosition(Object monster) {
        return monsterPosition.get(monster);
    }

    /**
     * Sets the new position of a monster after a movement
     * @param monster The monster that's position is changing
     * @param newPosition The new position of the monster
     */
    public static void setMonsterPosition(Object monster, int[] newPosition) {
        monsterPosition.put(monster, newPosition);
    }

    public static void setItemPosition(Item newItem, int[] position) {
        itemPosition.put(newItem, position);
    }

    public static HashMap <Item, int[]> getListOfItems() {
        return itemPosition;
    }

    /**
     * This method removes an item from the HashMap of itemPosition
     * The reason it is removed rather than simply moved is that once an item
     * is in the player's inventory, it is no longer on the actual board itself
     * therefore we remove it entirely from here, though the object itself
     * still exists within the player's inventory
     * @param itemToRemove The item we are removing from the board
     */
    public static void removeItem(Item itemToRemove) {
        itemPosition.remove(itemToRemove);
    }

    /**
    * Returns the current position of the block on the board
    * @return the position of block as an Array of Integers containing its
    * X and Y coordinate
    */
    public static int[] getBlockPosition(Block blockToFind) {
        return blockPosition.get(blockToFind);
    }

    /**
    * Sets the new position of the block after it has been moved
    * @param newPosition the new position of the block as an Array of Integers
    * representing its X and Y coordinate 
    */
    public static void setBlockPosition(Block newBlock, int[] newPosition) {
        blockPosition.put(newBlock, newPosition);
    }

    public static HashMap<Block, int[]> getBlockPosition() {
        return blockPosition;
    }

    /**
     * Returns the tile contained at a given position
     * @param position an array of ints with the x and y position that
     *                 is being searched
     * @return a tile object that is the tile at the requested position
     */
    public static Tile getTileAt(int[] position) {
        return tilePosition.get(position[0]).get(position[1]);
    }

    /**
     * Sets a new tile with a given Tile and position, there is a check to
     * where if it is the first tile of a column, it sets it rather than
     * adds, this is to work around that the arraylist needs to be
     * initialised with a tile object already inside
     * @param tileCreated the Tile object that is to be created
     * @param position an array of ints containing the x and y coordinate of
     *                 the tile
     */
    public static void setNewTile(Tile tileCreated, int[] position) {
        if(position[1] == 0) {
            ArrayList<Tile> toAdd = new ArrayList<>();
            toAdd.add(tileCreated);
            tilePosition.add(toAdd);
        } else {
            tilePosition.get(position[0]).add(position[1], tileCreated);
        }
    }

    public static void changeTile(Tile tileToAdd, int[] position) {
        tilePosition.get(position[0]).set(position[1], tileToAdd);
    }

    public static ArrayList<ArrayList<Tile>> getTileList() {
        return tilePosition;
    }

}
