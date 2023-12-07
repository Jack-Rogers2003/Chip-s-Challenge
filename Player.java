import java.util.ArrayList;

public class Player {
    private static final String IMAGE_FILE = "player.png";
    private static final int TICK_COUNT = 1;
    private static final ArrayList<Item> playerItems = new ArrayList<>();

    public static String getImageFile() {
        return IMAGE_FILE;
    }

    public static int getTickCount() {
        return TICK_COUNT;
    }

    public static void addPlayerItems(Item itemToAdd) {
        playerItems.add(itemToAdd);
    }

    public static ArrayList<Item> getPlayerItems() {
        return playerItems;
    }

    public static void removeItem(Item itemToRemove) {
        playerItems.remove(itemToRemove);
    }

    public static void resetItems() {
        playerItems.clear();
    }
}
