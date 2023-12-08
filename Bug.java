/**
 * This class reprensents the bug monster and its behaviours
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */

public class Bug extends Monster{

    private static final int TICK = 3;
    private static final String IMAGE_FILE = "bug.png";
    private final String side;

    /**
     * Constructer that creates a bug
     * 
     * @oaram sideToSet the side of the board to set the bug to
     */
    public Bug(String sideToSet) {
        side = sideToSet;
    }

    /**
     * Method that retrieves the side of the board
     *
     * @return the side of the board
     */
    public String getSide() {
        return side;
    }

    /**
     * Method that retrieves the tick count
     *
     * @return the current tick
     */
    public int getTick() {
        return TICK;
    }

    /**
     * Method that retrieves the next movement of a bug
     *
     * @return the x and y coordinates of the bugs move
     */
    public int[] getNextMovement() {
        return new int[]{0,0};
    }

    /**
     * Method that retrieves the file containing the png of a bug
     *
     * @return the file containing the image
     */
    public String getImageFile() {
        return IMAGE_FILE;
    }
}
