/**
 * Abstract class that is extended by the items used by the game, forces
 * implementation of shared properties and allows all items to be stored
 * together
 * @author Jack Rogers
 * @version 1.0
 */
public abstract class Item {
    /**
     * Returns the name of the file that has the image of the item
     * @return file name
     */
    abstract String getImageFile();
}
