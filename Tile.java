
/**
 * Class that encompasses all Tiles and dictates an actors movement on it
 * @author Jack Jones, Dan Cross
 * @version 1.0
 */

public abstract class Tile {

    private Boolean moveability;

    /**
     * Constructor for the Tile class
     *
     * @param moveability Boolean input that dictates whether
     *                    an actor can move on the tile or not
     */
    Tile(Boolean moveability) {
        this.moveability = moveability;
    }


    public Boolean getMoveability() {
        return this.moveability;
    }
}

