
/**
 * Class that encompasses all Tiles and dictates an actors movement on it
 * @author Jack Jones, Dan Cross
 * @version 1.0
 */

public abstract class Tile {

    private abstract Boolean moveability;
    private abstract String imageFile;

    public abstract Boolean getMoveability();
    public abstract String getFilePath();    

}

