import java.util.ArrayList;

/**
 * Class representing actors in the game, such as players, monsters (pink balls, bugs, frogs), and blocks.
 * Actors have various behaviors and interactions in the game world.
 *
 * @author Benji Brew
 * @version 1.1
 */
public class Actor {

    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final ArrayList<Block> blocks = new ArrayList<>();


    /**
     * Method to set and add a monster to list of monsters
     * 
     * @param toAdd the monster to add to the list
    */
    public void setNewMonster(Monster toAdd) {
        monsters.add(toAdd);
    }
    
    /**
     * Method that retrieves the list of monsters
     *
     * @return the list of monsters
    */
    public ArrayList<Monster> getListOfMonsters() {
        return monsters;
    }
    
    /**
     * Method sets and adds a block to the list of blocks
    */
    public void setNewBlock() {
        Block block = new Block();
        blocks.add(block);
    }

    /**
     * Method that clears the list of blocks and monsters
    */
    public void reset() {
        monsters.clear();
        blocks.clear();
    }
}
