public class PinkBall extends Monster {
    private static final int TICK = 3;
    private static final String IMAGE_FILE = "ball.png";
    private String direction;

    public PinkBall(String startingDirection) {
        direction = startingDirection;
    }

    public int getTick() {
        return TICK;
    }

    public String getDirection() {
        return direction;
    }

    public int[] getNextMovement() {
        int[] position = PositionManager.getMonsterPosition(this);
        switch (direction) {
            case "U":
                position[1] = position[1] - 1;
            case "D":
                position[1] = position[1] + 1;
            case "L":
                position[0] = position[0] - 1;
            case "R":
                position[0] = position[0] + 1;
        }
        return position;
    }

    public void changePosition() {
        switch (direction) {
            case "U":
                direction = "D";
            case "D":
                direction = "U";
            case "L":
                direction = "R";
            case "R":
                direction = "L";
        }
    }

    public String getImageFile() {
        return IMAGE_FILE;
    }
}
