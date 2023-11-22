import javafx.util.Pair;
import java.util.HashMap;

/**
 * Class that is used to track the positons of objects on the board
 * @author Jack Rogers
 * @version 1.0
 */
public class Position {
    private static Integer[] playerPosition;
    private static HashMap<Object, Integer[]> monsterPosition;
    private static HashMap<Item, Integer[]> itemPosition;
    private static Integer[] blockPosition;

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
     * Sets the new position of a monster after a movement
     * @param monster The monster that's position is changing
     * @param newPosition The new position of the monster
     */
    public void setMonsterPosition(Object monster, Integer[] newPosition) {
        monsterPosition.put(monster, newPosition);
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
}
