import java.util.ArrayList;

public class Frog extends Monster {
    private static final int TICK = 3;
    private static final String IMAGE_FILE = "frog.png";
    private boolean playerFound;


    public int getTick() {
        return TICK;
    }

    public void getNextMovement() {
        int[] frogPosition = PositionManager.getMonsterPosition(this);
        playerFound = false;
        search(frogPosition, new ArrayList<>(), new ArrayList<>());
    }

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

    public boolean playerPosCheck(int[] position) {
        int[] playerPosition = PositionManager.getPlayerPosition();
        return playerPosition[0] == position[0] &&
                playerPosition[1] == position[1];
    }

    public String getImageFile() {
        return IMAGE_FILE;
    }
}
