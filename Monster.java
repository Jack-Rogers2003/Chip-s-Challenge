/**
 * Abstract class that is extended by all monsters of the game, forces shared
 * methods between them and allows them to be stored together by Monster
 * @author Jack Rogers
 * @version 1.0
 */
public abstract class Monster {
    /**
     * Get the next movement of a monster
     */
    public abstract void getNextMovement();

    /**
     * Gets the tick rate of a monster
     * @return tick rate
     */
    public abstract int getTick();

    /**
     * Gets the name of the file that stores the image of the monster
     * @return file name
     */
    public abstract String getImageFile();

}
