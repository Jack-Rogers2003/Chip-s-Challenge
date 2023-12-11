import java.util.ArrayList;

/**
 * Class representing actors in the game, such as players, monsters
 * (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 * @author Benji Brew
 * @author Uzair Khan
 * @version 1.0
 */
public class Actor {

    //ArrayList of Monsters in the game
    private final ArrayList<Monster> monsters = new ArrayList<>();
    //ArrayList of Blocks in the game
    private final ArrayList<Block> blocks = new ArrayList<>();

    /**
     * Adds a monster to the ArrayList of monsters
     * @param toAdd Monster being added to the game
     */
    public void setNewMonster(Monster toAdd) {
        monsters.add(toAdd);
    }

    /**
     * Returns an arrayList of Monsters in the game
     * @return ArrayList of Monsters in the game
     */
    public ArrayList<Monster> getListOfMonsters() {
        return monsters;
    }

    /**
     * Adds a block to the arrayList of blocks
     * @param toAdd block to be added
     */
    public void setNewBlock(Block toAdd) {
        blocks.add(toAdd);
    }

    /**
     * Returns an arrayList of blocks in the game
     * @return arraylist of blocks in the game
     */
    public ArrayList<Block> getListOfBlocks() {
        return blocks;
    }

    /**
     * Removes a block from the ArrayList of blocks and so the game
     * @param toRemove Block to be removed
     */
    public void removeBlock(Block toRemove) {
        blocks.remove(toRemove);
    }

    /**
     * Resets the monsters and blocks list, this is done to prevent
     * issues when going from one level to another
     */
    public void reset() {
        monsters.clear();
        blocks.clear();
    }
}
