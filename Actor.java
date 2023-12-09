import java.util.ArrayList;

/**
 * Class representing actors in the game, such as players, monsters (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 *
 * @author Benji Brew
 * @version 1.0
 */
public class Actor {

    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final ArrayList<Block> blocks = new ArrayList<>();

    public void setNewMonster(Monster toAdd) {
        monsters.add(toAdd);
    }

    public ArrayList<Monster> getListOfMonsters() {
        return monsters;
    }

    public void setNewBlock(Block toAdd) {
        blocks.add(toAdd);
    }

    public ArrayList<Block> getListOfBlocks() {
        return blocks;
    }

    public void removeBlock(Block toRemove) {
        blocks.remove(toRemove);
    }

    public void reset() {
        monsters.clear();
        blocks.clear();
    }
}
