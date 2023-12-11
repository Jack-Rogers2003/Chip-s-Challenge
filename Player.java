import java.util.ArrayList;

/**
 * Class that handles the Player actor on the board and its needed
 * properties and operations
 * @author Benj Brew
 * @author Uzair Khan
 * @version 1.1
 */
public class Player {
    //Name of the file that has the image of the Player
    private static final String IMAGE_FILE = "player.png";
    //Tick rate of the player's movement
    private static final int TICK_COUNT = 1;
    //List of the player's current items
    private static final ArrayList<Item> PLAYER_ITEMS = new ArrayList<>();

    /**
     * Gets the name of the file that has the image of the player
     * @return file name
     */
    public static String getImageFile() {
        return IMAGE_FILE;
    }

    /**
     * Gets the tick rate of the player
     * @return tick rate
     */
    public static int getTickCount() {
        return TICK_COUNT;
    }

    /**
     * Adds an item to the player's inventory
     * @param itemToAdd item to be added
     */
    public static void addPlayerItems(Item itemToAdd) {
        PLAYER_ITEMS.add(itemToAdd);
    }

    /**
     * Returns all items the player has collected
     * @return ArrayList of items the player has
     */
    public static ArrayList<Item> getPlayerItems() {
        return PLAYER_ITEMS;
    }

    /**
     * Resets the player's inventory, done to prevent transfer of items
     * between levels
     */
    public static void resetItems() {
        PLAYER_ITEMS.clear();
    }
}
