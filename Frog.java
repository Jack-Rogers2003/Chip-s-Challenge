import java.util.ArrayList;

public class Frog extends Monster {
    private static final int TICK = 1;
    private static final String IMAGE_FILE = "frog.png";
    private ArrayList<ArrayList<int[]>> nextMovement = new ArrayList<>();
    private static boolean playerFound = false;

    public int getTick() {
        return TICK;
    }

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

    public void search(int[] currentPos, ArrayList<int[]> visited) {
        visited.add(currentPos);
        if(!playerFound) {
            int[] movement = new int[2];
            //Checking tile above
            movement[0] = currentPos[0];
            movement[1] = currentPos[1] - 1;
            if (tileCheck(movement) && notVisited(movement, visited) && !playerFound) {
                if(foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Checking tile below
            movement[0] = currentPos[0];
            movement[1] = currentPos[1] + 1;
            if (tileCheck(movement) && notVisited(movement, visited) && !playerFound) {
                if(foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Check tile left
            movement[0] = currentPos[0] - 1;
            movement[1] = currentPos[1];
            if (tileCheck(movement) && notVisited(movement, visited) && !playerFound) {
                if(foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
            //Check right tile
            movement[0] = currentPos[0] + 1;
            movement[1] = currentPos[1];
            if (tileCheck(movement) && notVisited(movement, visited) && !playerFound) {
                if(foundPlayer(movement)) {
                    visited.add(movement);
                    addToMovement(visited);
                } else {
                    search(movement, visited);
                }
            }
        }
    }

    private void addToMovement(ArrayList<int[]> toAdd) {
        ArrayList<int[]> createdList = new ArrayList<>();
        for(int i = 0; i < toAdd.size(); i ++) {
            int x = toAdd.get(i)[0];
            int y = toAdd.get(i)[1];
            createdList.add(new int[] {x, y});
        }
        nextMovement.add(createdList);
    }


    private void getFinalMove(ArrayList<ArrayList<int[]>> possibleMoves) {
        int[] nextMove = new int[2];
        int distance = 10000000;
        for (int i = 0; i < possibleMoves.size(); i++) {
            ArrayList<int[]> currentCheck = possibleMoves.get(i);
            dumbVisited(currentCheck);
            if(currentCheck.size() < distance) {
                nextMove[0] = currentCheck.get(1)[0];
                nextMove[1] = currentCheck.get(1)[1];
                distance = currentCheck.size();
            }
        }
        PositionManager.setMonsterPosition(this, nextMove);
    }

    private boolean notVisited(int[] position, ArrayList<int[]> visited) {
        for(int i = 0; i < visited.size(); i++) {
            int visitedX = visited.get(i)[0];
            int visitedY = visited.get(i)[1];
            if(position[0] == visitedX && position[1] == visitedY) {
                return false;
            }
        }
        return true;
    }

    private boolean foundPlayer(int[] positionToCheck) {
        int[] playerPosition = PositionManager.getPlayerPosition();
        if(positionToCheck[0] == playerPosition[0] && positionToCheck[1] == playerPosition[1]) {
            playerFound = true;
            return true;
        }
        return false;
    }

    private void dumbVisited(ArrayList<int[]> visited) {
        for(int i = 0; i < visited.size(); i++) {
            System.out.println(visited.get(i)[0] + " " + visited.get(i)[1]);
        }
        System.out.println("New");
    }
    private void printVisisted(ArrayList<ArrayList<int[]>> visited) {
        for(int i = 0; i < visited.size(); i++) {
            for(int j = 0; j < visited.get(i).size(); j++) {
                System.out.println(visited.get(i).get(j)[0] + " " + visited.get(i).get(j)[1]);
            }
        }
        System.out.println("new");
    }

    private boolean tileCheck(int[] tilePosition) {
        if(!Movement.outOfBoundsCheck(tilePosition) && !Movement.monsterOrBlockCheck(tilePosition)) {
            Tile tile = PositionManager.getTileAt(tilePosition);
            return (tile instanceof Path || tile instanceof Trap);
        }
        return false;
    }


    public String getImageFile() {
        return IMAGE_FILE;
    }
}
