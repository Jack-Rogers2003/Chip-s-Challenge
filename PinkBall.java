/**
 * Class that handles the PinkBall monster and its associated properties and
 * operations
 */
public class PinkBall extends Monster {
    //Tick rate the PinkBall moves at
    private static final int TICK = 1;
    //name of the file containing the image of the PinkBall
    private static final String IMAGE_FILE = "ball.png";
    //The current direction the PinkBall is moving in
    private String direction;

    /**
     * Constructor for PinkBall, sets the starting direction it's moving in
     * @param startingDirection starting direction for the PinkBall
     */
    public PinkBall(String startingDirection) {
        direction = startingDirection;
    }

    /**
     * Gets the tick rate of the PinkBall
     * @return tick rate
     */
    public int getTick() {
        return TICK;
    }

    /**
     * Gets the current direction of the PinkBall
     * @return direction of the PinkBall
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Gets the next Movement of the PinkBall depending on its current
     * direction
     */
    public void getNextMovement() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] newPosition = new int[2];
        newPosition[0] = position[0];
        newPosition[1] = position[1];
        switch (direction) {
            case "U" -> newPosition[1] = newPosition[1] - 1;
            case "D" -> newPosition[1] = newPosition[1] + 1;
            case "L" -> newPosition[0] = newPosition[0] - 1;
            case "R" -> newPosition[0] = newPosition[0] + 1;
        }
        if (Movement.outOfBoundsCheck(newPosition) || Movement.
                monsterOrBlockCheck(newPosition)) {
            changeDirection();
        } else {
            Tile tile = PositionManager.getTileAt(newPosition);
            if (!(tile instanceof Path || tile instanceof Button ||
                    tile instanceof Trap)) {
                changeDirection();
            } else {
                if (tile instanceof Button) {
                    ((Button) tile).setIsPressed();
                }
                PositionManager.setMonsterPosition(this, newPosition);
            }
        }
    }

    /**
     * Changes the direction of the Pink ball depending on its current
     * direction
     */
    public void changeDirection() {
        switch (direction) {
            case "U" -> direction = "D";
            case "D" -> direction = "U";
            case "L" -> direction = "R";
            case "R" -> direction = "L";
        }
    }

    /**
     * Returns the name of the file with the image of the PinkBall
     * @return file name
     */
    public String getImageFile() {
        return IMAGE_FILE;
    }
}
