import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Movement {

    public void playerMovement(String nextMove) {
        int[] playerPosition = PositionManager.getPlayerPosition();
        int[] newPosition = new int[2];
        switch (nextMove) {
            case "U" -> {
                newPosition[1] = playerPosition[1] - 1;
                newPosition[0] = playerPosition[0];
            }
            case "D" -> {
                newPosition[1] = playerPosition[1] + 1;
                newPosition[0] = playerPosition[0];
            }
            case "L" -> {
                newPosition[0] = playerPosition[0] - 1;
                newPosition[1] = playerPosition[1];
            }
            case "R" -> {
                newPosition[0] = playerPosition[0] + 1;
                newPosition[1] = playerPosition[1];
            }
        }
        if(!outOfBoundsCheck(newPosition) && tileCheck(newPosition)) {
            if(itemCheck(newPosition)) {
                getItemForPlayer(newPosition);
            }
            PositionManager.setPlayerPosition(newPosition);
            monsterCheck(newPosition);
        }
    }

    public void monsterCheck(int[] position) {
        Actor boardActors = Board.getActors();
        ArrayList<Monster> listOfMonsters = boardActors.getListOfMonsters();
        for (Monster currentMonster : listOfMonsters) {
            int[] monsterPosition = PositionManager.getMonsterPosition(currentMonster);
            if (monsterPosition[0] == position[0] && monsterPosition[1] == position[1]) {
                if (currentMonster instanceof Frog) {
                    BoardGUI.setGameEnd("Frog");
                } else if (currentMonster instanceof PinkBall) {
                    BoardGUI.setGameEnd("Pink Ball");
                } else if (currentMonster instanceof Bug) {
                    BoardGUI.setGameEnd("Bug");
                }
            }
        }
    }

    public boolean outOfBoundsCheck(int[] currentPosition) {
        int positionX = currentPosition[0];
        int positionY = currentPosition[1];
        int[] size = BoardGUI.getBoardSize();
        int gridWidth = size[0];
        int gridHeight = size[1];
        return positionY < 0 || positionY > gridHeight - 1 || positionX < 0 ||
                positionX > gridWidth - 1;
    }

    public boolean tileCheck(int[] nextPosition) {
        Tile tileToCheck = PositionManager.getTileAt(nextPosition);
        if (tileToCheck instanceof Dirt) {
            PositionManager.changeTile(new Path(), nextPosition);
        } else if (tileToCheck instanceof Door) {
            doorCheck((Door) tileToCheck, nextPosition);
        } else if (tileToCheck instanceof Ice) {
            iceMovement(PositionManager.getPlayerPosition(), nextPosition);
            //Returns false as iceMovement sets the movement itself due to its
            //unique property and need for checking
            return false;
        } else if (tileToCheck instanceof Exit) {
            BoardGUI.setGameEnd("Exit");
        } else if (tileToCheck instanceof  Water) {
            BoardGUI.setGameEnd("Water");
        }
        return PositionManager.getTileAt(nextPosition).getCanMoveOn();
    }

    public void iceMovement(int[] currentPosition, int[] nextPosition) {
        Ice iceTile = (Ice) PositionManager.getTileAt(nextPosition);
        String corner = iceTile.getCorner();
        String direction = getPlayerDirection(currentPosition, nextPosition);
        int[] icePosition = new int[2];
        icePosition[0] = nextPosition[0];
        icePosition[1] = nextPosition[1];
        nextPosition = findNextIceMovement(corner, direction, nextPosition);
        if (outOfBoundsCheck(nextPosition)) {
            PositionManager.setPlayerPosition(icePosition);
        } else if (!PositionManager.getTileAt(nextPosition).getCanMoveOn()) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
        } else if (PositionManager.getTileAt(nextPosition) instanceof Ice) {
            iceItemCheck(icePosition);
            iceMovement(icePosition, nextPosition);
        } else if (tileCheck(nextPosition)){
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(nextPosition);
        }
    }

    public int[] findNextIceMovement(String corner, String direction,
                                    int[] nextPosition) {
        switch (corner) {
            case "N":
                switch (direction) {
                    case "L" -> nextPosition[0] = nextPosition[0] - 1;
                    case "R" -> nextPosition[0] = nextPosition[0] + 1;
                    case "U" -> nextPosition[1] = nextPosition[1] - 1;
                    case "D" -> nextPosition[1] = nextPosition[1] + 1;
                }
                break;
            case "L":
                switch (direction) {
                    case "L", "U" -> nextPosition[1] = nextPosition[1] - 1;
                    case "R", "D" -> nextPosition[0] = nextPosition[0] + 1;
                }
                break;
            case "B":
                switch (direction) {
                    case "R", "U" -> nextPosition[1] = nextPosition[1] - 1;
                    case "L", "D" -> nextPosition[0] = nextPosition[0] - 1;
                }
                break;
            case "T":
                switch (direction) {
                    case "U", "R" -> nextPosition[0] = nextPosition[0] + 1;
                    case "D", "L" -> nextPosition[1] = nextPosition[1] + 1;
                }
                break;
            case "R":
                switch (direction) {
                    case "D", "R" -> nextPosition[1] = nextPosition[1] + 1;
                    case "U", "L" -> nextPosition[0] = nextPosition[0] - 1;
                }
                break;
        }
        return nextPosition;
    }

    public void iceItemCheck(int[] icePosition) {
        if(itemCheck(icePosition)) {
            getItemForPlayer(icePosition);
        }
    }

    public String getPlayerDirection(int[] currentPosition, int[] nextPosition) {
        if(nextPosition[0] - currentPosition[0] == -1) {
            return "L";
        } else if (nextPosition[0] - currentPosition[0] == 1) {
            return "R";
        } else if (nextPosition[1] - currentPosition[1] == -1) {
            return "U";
        } else {
            return "D";
        }
    }

    public void doorCheck(Door doorToCheck, int[] nextPosition) {
        ArrayList<Item> itemList = Player.getPlayerItems();
        for (Item item : itemList) {
            if (item instanceof Key newKey) {
                if (Objects.equals(newKey.getColour(), doorToCheck.getColour())) {
                    PositionManager.changeTile(new Path(), nextPosition);
                    Player.removeItem(item);
                    //As we've already found and used the item, no need to
                    //continue the for loop
                    break;
                }
            }
        }
    }

    public boolean itemCheck(int[] positionToCheck) {
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for (Map.Entry<Item, int[]> entry : itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if(itemPosition[0] == positionToCheck[0] && itemPosition[1] == positionToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    public void getItemForPlayer(int[] itemToGetPosition) {
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for (Map.Entry<Item, int[]> entry : itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if(itemPosition[0] == itemToGetPosition[0] && itemPosition[1] == itemToGetPosition[1]) {
                Player.addPlayerItems(entry.getKey());
                PositionManager.removeItem(entry.getKey());
                break;
            }
        }
    }
}
