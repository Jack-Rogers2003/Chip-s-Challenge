import java.util.ArrayList;

/**
 * Handles the Frog Monster and its properties and associated operations
 * @author Jack Rogers
 * @version 1.0
 */
public class Frog extends Monster {
    //Tick rate of the Frog
    private static final int TICK = 1;
    //name of the file containing the image for the frog
    private static final String IMAGE_FILE = "frog.png";
    //Maximum distance the frog can be, used when checking for the best move
    //to make
    private static final int MAX_DISTANCE = 10000000;
    //ArrayList containing an ArrayList of ints that are the possible moves
    //for the frog
    private ArrayList<ArrayList<int[]>> nextMovement = new ArrayList<>();
    //Whether the player has been found or not
    private static boolean playerFound = false;

    /**
     * Returns the tick rate of the frog
     * @return tick rate
     */
    public int getTick() {
        return TICK;
    }

    /**
     * Gets the next Movement for the frog, checks each four directions it can
     * take and calls for the search algorithm to find a path from each of
     * them
     */
    public void getNextMovement() {
        int[] frogPosition = PositionManager.getMonsterPosition(this);
        int[] movement = new int[2];
        ArrayList<int[]> startingList = new ArrayList<>();
        startingList.add(frogPosition);
        playerFound = false;
        //Check tile left
        movement[0] = frogPosition[0];
        movement[1] = frogPosition[1] - 1;
        if (tileCheck(movement)) {
            search(movement, startingList);
        }
        playerFound = false;
        movement[0] = frogPosition[0] - 1;
        movement[1] = frogPosition[1];
        if (tileCheck(movement)) {
            search(movement, startingList);
        }
        playerFound = false;
        movement[0] = frogPosition[0] + 1;
        movement[1] = frogPosition[1];
        if (tileCheck(movement)) {
            search(movement, startingList);
        }
        playerFound = false;
        movement[0] = frogPosition[0];
        movement[1] = frogPosition[1] + 1;
        if (tileCheck(movement)) {
            search(movement, startingList);
        }
        getFinalMove(nextMovement);
        nextMovement.clear();
        playerFound = false;
    }

    /**
     * Searches through the maze until it finds the player (the shortest path)
     * and adds the path it took to the list of movements
     * @param currentPos Current position the frog is at
     * @param visited Visited positions
     */
    public void search(int[] currentPos, ArrayList<int[]> visited) {
        visited.add(currentPos);
        if (!playerFound) {
            int[] movement = new int[2];
            //Checking tile above
            movement[0] = currentPos[0];
            movement[1] = currentPos[1] - 1;
            if (tileCheck(movement) && notVisited(movement, visited) &&
                    !playerFound) {
                if (foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Checking tile below
            movement[0] = currentPos[0];
            movement[1] = currentPos[1] + 1;
            if (tileCheck(movement) && notVisited(movement, visited) &&
                    !playerFound) {
                if (foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Check tile left
            movement[0] = currentPos[0] - 1;
            movement[1] = currentPos[1];
            if (tileCheck(movement) && notVisited(movement, visited) &&
                    !playerFound) {
                if (foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Check right tile
            movement[0] = currentPos[0] + 1;
            movement[1] = currentPos[1];
            if (tileCheck(movement) && notVisited(movement, visited) &&
                    !playerFound) {
                if (foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
        }
    }

    /**
     * Adds a position to nextMovement
     * @param toAdd position to be added
     */
    private void addToMovement(ArrayList<int[]> toAdd) {
        ArrayList<int[]> createdList = new ArrayList<>();
        for (int i = 0; i < toAdd.size(); i++) {
            int x = toAdd.get(i)[0];
            int y = toAdd.get(i)[1];
            createdList.add(new int[] {x, y});
        }
        nextMovement.add(createdList);
    }

    /**
     * Finds the best move the frog can take by finding the shortest route it
     * can take
     * @param possibleMoves the possible paths to the player it can take
     */
    private void getFinalMove(ArrayList<ArrayList<int[]>> possibleMoves) {
        int[] nextMove = new int[2];
        int distance = MAX_DISTANCE;
        for (ArrayList<int[]> currentCheck : possibleMoves) {
            if (currentCheck.size() < distance) {
                nextMove[0] = currentCheck.get(1)[0];
                nextMove[1] = currentCheck.get(1)[1];
                distance = currentCheck.size();
            }
        }
        PositionManager.setMonsterPosition(this, nextMove);
    }

    /**
     * Checks whether the position hasn't been visited yet
     * @param position position being checked
     * @param visited positions that have been visited
     * @return boolean if it has been visited or not
     */
    private boolean notVisited(int[] position, ArrayList<int[]> visited) {
        for (int[] ints : visited) {
            int visitedX = ints[0];
            int visitedY = ints[1];
            if (position[0] == visitedX && position[1] == visitedY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the player is at the position being checked
     * @param positionToCheck the position being checked
     * @return whether the position is the same as the player's position
     */
    private boolean foundPlayer(int[] positionToCheck) {
        int[] playerPosition = PositionManager.getPlayerPosition();
        if (positionToCheck[0] == playerPosition[0] && positionToCheck[1] ==
                playerPosition[1]) {
            playerFound = true;
            return true;
        }
        return false;
    }

    /**
     * Checks if the tile is a valid one for the frog to move on
     * @param tilePosition Position of the tile being checked
     * @return boolean if it can be moved in or not
     */
    private boolean tileCheck(int[] tilePosition) {
        if (!Movement.outOfBoundsCheck(tilePosition) && !Movement.
                monsterOrBlockCheck(tilePosition)) {
            Tile tile = PositionManager.getTileAt(tilePosition);
            return (tile instanceof Path || tile instanceof Trap);
        }
        return false;
    }

    /**
     * Returns the name of the file containing the image of the frog
     * @return file name
     */
    public String getImageFile() {
        return IMAGE_FILE;
    }
}