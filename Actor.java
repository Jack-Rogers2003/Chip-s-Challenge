import java.util.ArrayList;

/**
 * Class representing actors in the game, such as players, monsters (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 *
 * @author Benji Brew
 * @version 1.0
 */
public class Actor {

    private PositionManager positionManager;
    private int gameTickMovementInterval;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public void setNewMonster(Monster toAdd) {
        monsters.add(toAdd);
    }

    public ArrayList<Monster> getListOfMonsters() {
        return monsters;
    }
}
