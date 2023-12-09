import java.util.ArrayList;

/**
 * Class that represents the frog monster with methods to track it's movement and position
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */

public class Frog extends Monster {
    private static final int TICK = 3;
    private static final String IMAGE_FILE = "frog.png";
    private boolean playerFound;


    /**
     * Method that checks the current tick the game is on
     *
     * @return the tick the game is on
     */
    public int getTick() {
        return TICK;
    }

    /**
     * Method that checks the next movemnt of a frog
     */
    public void getNextMovement() {
        int[] frogPosition = PositionManager.getMonsterPosition(this);
        playerFound = false;
        search(frogPosition, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Method that seraches for a player and specifies the actions that
     * the frog will perform if a player is or isn't found
     * 
     * @param currentPos the position of frog
     * @param checked a list of checked positions for a player
     * @param path the path the frog will take
     */
    public void search(int[] currentPos, ArrayList<int[]> checked, ArrayList<int[]> path) {
        if(!playerFound) {
            int[] newPos = new int[2];
            int[] up = new int[]{currentPos[0], currentPos[1] + 1};
            if (PositionManager.getTileAt(up) instanceof Path || PositionManager.getTileAt(up) instanceof Button && !Movement.outOfBoundsCheck(up)) {
                newPos[0] = up[0];
                newPos[1] = up[1];
                if (!playerPosCheck(newPos)) {
                }
            }
        }
    }

    /**
     * Method that checks the position of a player and the frog
     *
     * @param position the x and y coordinates of the players position
     * @return the boolean value of whether the positions of the frog and player
     * are the same
     */
    public boolean playerPosCheck(int[] position) {
        int[] playerPosition = PositionManager.getPlayerPosition();
        return playerPosition[0] == position[0] &&
                playerPosition[1] == position[1];
    }

    /**
     * Method that retrieves the image file of a frog
     *
     * @return the image file
     */
    public String getImageFile() {
        return IMAGE_FILE;
    }
}
