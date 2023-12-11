import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that is used to track the positions of objects on the board
 * @author Jack Rogers
 * @version 1.4
 */

public class PositionManager {
    //Position of the Player
    private static int[] playerPosition;
    //Hashmap of all Monster on the board and they Positions
    private static final HashMap<Monster, int[]> MONSTER_POSITION =
            new HashMap<>();
    //HashMap of Items on the board and their positions
    private static final HashMap<Item, int[]> ITEM_POSITION = new HashMap<>();
    //Hashmap of all blocks on the board and their positions
    private static final HashMap<Block, int[]> BLOCK_POSITION =
            new HashMap<>();
    //ArrayList containing an ArrayList of Tiles on the board, the tiles are
    //in the position representing their position on the board
    private static final ArrayList<ArrayList<Tile>> TILE_POSITION =
            new ArrayList<>();

    /**
     * Used for when first generating the game, sets all attributes to empty,
     * this is to prevent possible bugs or issues when transferring from one
     * level to another, such as a player's collected items carrying over
     */
    public static void resetPositions() {
        TILE_POSITION.clear();
        BLOCK_POSITION.clear();
        ITEM_POSITION.clear();
        MONSTER_POSITION.clear();
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
     * @param newPlayerPosition the new position of the player
     */
    public static void setPlayerPosition(int[] newPlayerPosition) {
        playerPosition = newPlayerPosition;
    }

    /**
     * Returns the position of a monster
     * @param monster the monster we are looking for the position of
     * @return The position of a monster
     */
    public static int[] getMonsterPosition(Object monster) {
        return MONSTER_POSITION.get(monster);
    }

    /**
     * Sets the new position of a monster after a movement
     * @param monster The monster that's position is changing
     * @param newPosition The new position of the monster
     */
    public static void setMonsterPosition(Monster monster, int[] newPosition) {
        MONSTER_POSITION.put(monster, newPosition);
    }

    /**
     * Sets the position of an item
     * @param newItem item being set position of
     * @param position position of item
     */
    public static void setItemPosition(Item newItem, int[] position) {
        ITEM_POSITION.put(newItem, position);
    }

    /**
     * Returns a Hashmap of the items on the board
     * @return hashmap of items
     */
    public static HashMap<Item, int[]> getListOfItems() {
        return ITEM_POSITION;
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
        ITEM_POSITION.remove(itemToRemove);
    }

    /**
     * Gets the position of a block
     * @param blockToFind block to get the position of
     * @return position of blockToFind
     */
    public static int[] getBlockPosition(Block blockToFind) {
        return BLOCK_POSITION.get(blockToFind);
    }

    /**
     * Sets the new position of a block
     * @param newBlock block having position changed
     * @param newPosition new position of block
     */
    public static void setBlockPosition(Block newBlock, int[] newPosition) {
        BLOCK_POSITION.put(newBlock, newPosition);
    }

    /**
     * Returns a hashMap that is contains the blocks on the board
     * @return hashmap of blocks
     */
    public static HashMap<Block, int[]> getBlockPosition() {
        return BLOCK_POSITION;
    }

    /**
     * Removes a block from the board
     * @param toRemove block to remove
     */
    public static void removeBlock(Block toRemove) {
        BLOCK_POSITION.remove(toRemove);
    }

    /**
     * Returns the tile contained at a given position
     * @param position position of Tile
     * @return a tile object that is the tile at the requested position
     */
    public static Tile getTileAt(int[] position) {
        return TILE_POSITION.get(position[0]).get(position[1]);
    }

    /**
     * Sets a new tile with a given Tile and position, there is a check to
     * where if it is the first tile of a column, it sets it rather than
     * adds, this is to work around that the arraylist needs to be
     * initialised with a tile object already inside
     * @param tileCreated the Tile object that is to be created
     * @param position The positon of the created tile
     */
    public static void setNewTile(Tile tileCreated, int[] position) {
        if (position[1] == 0) {
            ArrayList<Tile> toAdd = new ArrayList<>();
            toAdd.add(tileCreated);
            TILE_POSITION.add(toAdd);
        } else {
            TILE_POSITION.get(position[0]).add(position[1], tileCreated);
        }
    }

    /**
     * Changes what a tile is at a position
     * @param tileToAdd tile that is replacing another tile
     * @param position position of tile being replaced
     */
    public static void changeTile(Tile tileToAdd, int[] position) {
        TILE_POSITION.get(position[0]).set(position[1], tileToAdd);
    }

}
