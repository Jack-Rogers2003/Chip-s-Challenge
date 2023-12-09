public class PinkBall extends Monster {
    private static final int TICK = 1;
    private static final String IMAGE_FILE = "ball.png";
    private String direction;

    public PinkBall(String startingDirection) {
        direction = startingDirection;
    }

    public int getTick() {
        return TICK;
    }

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
        if (Movement.outOfBoundsCheck(newPosition) || Movement.monsterOrBlockCheck(newPosition)) {
            changeDirection();
        } else {
            Tile tile = PositionManager.getTileAt(newPosition);
            if (!(tile instanceof Path || tile instanceof Button || tile instanceof Trap)) {
                changeDirection();
            } else {
                if(tile instanceof Button) {
                    ((Button) tile).setIsPressed();
                }
                PositionManager.setMonsterPosition(this, newPosition);
            }
        }
    }

    public void changeDirection() {
        switch (direction) {
            case "U" -> direction = "D";
            case "D" -> direction = "U";
            case "L" -> direction = "R";
            case "R" -> direction = "L";
        }
    }

    public String getImageFile() {
        return IMAGE_FILE;
    }
}
