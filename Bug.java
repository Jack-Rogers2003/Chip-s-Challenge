import java.util.Objects;

/**
 * Class that handles the bug Monster, and it's associated properties and
 * operations
 * @author Jack Rogers
 * @version 1.0
 */
public class Bug extends Monster {

    //The ticks that the bug move on
    private static final int TICK = 2;
    //Name of the file that contains the bug's image
    private static final String IMAGE_FILE = "bug.png";
    //The side of which the bug moves against
    private final String side;
    //Used to know the direction the bug is in to calculate movement
    private String direction = "U";

    /**
     * Constructor for the bug, sets the side of which they move against
     * @param sideToSet side they will move against
     */
    public Bug(String sideToSet) {
        side = sideToSet;
    }

    /**
     * Returns the tick of the Bug
     * @return int that is the tick
     */
    public int getTick() {
        return TICK;
    }

    /**
     * Returns the side the bug moves against
     * @return String side of the bug
     */
    public String getSide() {
        return side;
    }

    /**
     * Gets the movement of the bug, checks the current direction it's moving
     * in and calls the method that matches it
     */
    public void getNextMovement() {
        if (Objects.equals(direction, "U")) {
            upMovement();
        } else if (Objects.equals(direction, "R")) {
            rightMovement();
        } else if (Objects.equals(direction, "L")) {
            leftMovement();
        } else if (Objects.equals(direction, "D")) {
            downMovement();
        }
    }

    /**
     * Movement if the bug direction is down
     */
    private void downMovement() {
        if (Objects.equals(side, "L")) {
            if (right()) {
                direction = "R";
            } else if (down()) {
                direction = "D";
            } else if (left()) {
                direction = "L";
            } else if (up()) {
                direction = "U";
            }
        } else if (Objects.equals(side, "R")) {
            if (left()) {
                direction = "L";
            } else if (down()) {
                direction = "D";
            } else if (right()) {
                direction = "R";
            } else if (up()) {
                direction = "U";
            }
        }
    }

    /**
     * Movement if the bug is facing left
     */
    private void leftMovement() {
        if (Objects.equals(side, "L")) {
            if (left()) {
                direction = "L";
            } else if (down()) {
                direction = "D";
            } else if (up()) {
                direction = "U";
            } else if (right()) {
                direction = "R";
            }
        } else if (Objects.equals(side, "R")) {
            if (up()) {
                direction = "U";
            } else if (left()) {
                direction = "L";
            } else if (down()) {
                direction = "D";
            } else if (right()) {
                direction = "R";
            }
        }
    }

    /**
     * Movement if the bug is facing right
     */
    private void rightMovement() {
        if (Objects.equals(side, "L")) {
            if (up()) {
                direction = "U";
            } else if (right()) {
                direction = "R";
            } else if (down()) {
                direction = "D";
            } else if (left()) {
                direction = "L";
            }
        } else if (Objects.equals(side, "R")) {
            if (down()) {
                direction = "D";
            } else if (right()) {
                direction = "R";
            } else if (left()) {
                direction = "L";
            } else if (up()) {
                direction = "U";
            }
        }
    }

    /**
     * Movement if the bug is facing up
     */
    private void upMovement() {
        if (Objects.equals(side, "L")) {
            if (left()) {
                direction = "L";
            } else if (up()) {
                direction = "U";
            } else if (right()) {
                direction = "R";
            } else if (down()) {
                direction = "D";
            }
        } else if (Objects.equals(side, "R")) {
            if (right()) {
                direction = "R";
            } else if (up()) {
                direction = "U";
            } else if (left()) {
                direction = "L";
            } else if (down()) {
                direction = "D";
            }
        }
    }

    /**
     * Checks if the next move the bug will make is valid, and if so sets the
     * bug's position and returns true, else it returns false
     * @param nextPosition the next position the bug will move in
     * @return boolean whether the move is a valid one
     */
    private boolean setPosition(int[] nextPosition) {
        if (tileCheck(nextPosition)) {
            if (PositionManager.getTileAt(nextPosition) instanceof Button) {
                ((Button) PositionManager.getTileAt(nextPosition)).
                        setIsPressed();
            }
            PositionManager.setMonsterPosition(this, nextPosition);
            return true;
        }
        return false;
    }

    /**
     * Checks if the downwards movement is a valid move, and returns true if so,
     * and false if not
     * @return boolean whether it's a valid move or not
     */
    private boolean down() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1] + 1;
        return setPosition(nextPosition);
    }

    /**
     * Checks if the right movement is a valid move, and returns true if so,
     * and false if not
     * @return boolean whether it's a valid move or not
     */
    private boolean right() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0] + 1;
        nextPosition[1] = position[1];
        return setPosition(nextPosition);
    }

    /**
     * Checks if the left movement is a valid move, and returns true if so,
     * and false if not
     * @return boolean whether it's a valid move or not
     */
    private boolean left() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0] - 1;
        nextPosition[1] = position[1];
        return setPosition(nextPosition);
    }

    /**
     * Checks if the upwards movement is a valid move, and returns true if so,
     * and false if not
     * @return boolean whether it's a valid move or not
     */
    private boolean up() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1] - 1;
        return setPosition(nextPosition);
    }

    /**
     * Checks whether the tile at the position is a valid one for the bug to
     * move on
     * @param tilePosition tile we're checking
     * @return boolean whether the tile is a valid one to move on
     */
    private boolean tileCheck(int[] tilePosition) {
        if (!Movement.outOfBoundsCheck(tilePosition) && !Movement.
                monsterOrBlockCheck(tilePosition)) {
            Tile tile = PositionManager.getTileAt(tilePosition);
            return (tile instanceof Path || tile instanceof Button ||
                    tile instanceof Trap);
        }
        return false;
    }

    /**
     * Gets the name of the image file of Bug
     * @return file name
     */
    public String getImageFile() {
        return IMAGE_FILE;
    }
}
