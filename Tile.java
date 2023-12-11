
/**
 * Class that encompasses all Tiles and dictates an actors movement on it
 * @author Jack Jones
 * @author Dan Cross
 * @version 1.0
 */

public abstract class Tile {
    /**
     * Gets whether a Tile can be moved on
     * @return can be moved on or not
     */
    public abstract boolean getCanMoveOn();

    /**
     * Get the name of the file containing the image of the tile
     * @return file name
     */
    public abstract String getImageFile();

}

