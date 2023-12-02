
/**
 * Class that encompasses all Tiles and dictates an actors movement on it
 * @author Jack Jones, Dan Cross
 * @version 1.1
 */

public abstract class Tile {
    public abstract Actor[] moveability;        // an array of the actor types that can move over the given tile
    public abstract String getImageFile();

    public Actor[] getMoveability() {
        return moveability;
    }
}

