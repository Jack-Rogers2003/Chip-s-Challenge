import java.util.Objects;

public class Bug extends Monster{

    private static final int TICK = 1;
    private static final String IMAGE_FILE = "bug.png";
    private final String side;
    //Used to know the direction the bug is in to calculate movement
    private String direction = "U";

    public Bug(String sideToSet) {
        side = sideToSet;
    }

    public int getTick() {
        return TICK;
    }

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
            if(down()) {
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

    private void upMovement() {
        if (Objects.equals(side, "L")) {
            if(left()) {
                direction = "L";
            } else if(up()) {
                direction = "U";
            } else if(right()) {
                direction = "R";
            } else if (down()) {
                direction = "D";
            }
        } else if (Objects.equals(side, "R")) {
            if(right()) {
                direction = "R";
            } else if(up()) {
                direction = "U";
            } else if (left()) {
                direction = "L";
            } else if (down()) {
                direction = "D";
            }
        }
    }

    private boolean setPosition(int[] nextPosition) {
        if (tileCheck(nextPosition)) {
            if (PositionManager.getTileAt(nextPosition) instanceof Button) {
                ((Button) PositionManager.getTileAt(nextPosition)).setIsPressed();
            }
            PositionManager.setMonsterPosition(this, nextPosition);
            return true;
        }
        return false;
    }
    
    private boolean down() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1] + 1;
        return setPosition(nextPosition);
    }

    private boolean right() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0] + 1;
        nextPosition[1] = position[1];
        return setPosition(nextPosition);
    }

    private boolean left() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0] - 1;
        nextPosition[1] = position[1];
        return setPosition(nextPosition);
    }

    private boolean up() {
        int[] position = PositionManager.getMonsterPosition(this);
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1] - 1;
        return setPosition(nextPosition);
    }

    private boolean tileCheck(int[] tilePosition) {
        if(!Movement.outOfBoundsCheck(tilePosition) && !Movement.monsterOrBlockCheck(tilePosition)) {
            Tile tile = PositionManager.getTileAt(tilePosition);
            return (tile instanceof Path || tile instanceof Button || tile instanceof Trap);
        }
        return false;
    }

    public String getImageFile() {
        return IMAGE_FILE;
    }
}
