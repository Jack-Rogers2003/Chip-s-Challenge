import java.util.*;

public class Movement {
    private boolean blockIsMoving = false;
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
        if(!outOfBoundsCheck(newPosition) &&
                tileCheck(newPosition) && trapCheck(playerPosition)) {
            if(itemCheck(newPosition)) {
                getItemForPlayer(newPosition);
            }
            if(blockCheck(newPosition)) {
                PositionManager.setPlayerPosition(newPosition);
                blockDeathCheck();
            }
        }
    }

    public boolean trapCheck(int[] position) {
        if(PositionManager.getTileAt(position) instanceof Trap) {
            return ((Trap) PositionManager.getTileAt(position)).getIsReleased();
        }
        return true;
    }

    public void blockDeathCheck() {
        int[] playerPosition = PositionManager.getPlayerPosition();
        Actor blocks = Board.getActors();
        ArrayList<Block> listOfBlocks = blocks.getListOfBlocks();
        for(Block currentBlock : listOfBlocks) {
            int[] blockPosition = PositionManager.getBlockPosition(currentBlock);
            if(blockPosition[0] == playerPosition[0] && blockPosition[1] ==playerPosition[1]) {
                BoardGUI.setGameEnd("Block");
            }
        }
    }

    public boolean blockCheck(int[] position) {
        Actor blocks = Board.getActors();
        ArrayList<Block> listOfBlocks = blocks.getListOfBlocks();
        for(Block currentBlock : listOfBlocks) {
            int[] newBlockPosition = new int[2];
            int[] blockPosition = PositionManager.getBlockPosition(currentBlock);
            newBlockPosition[0] = blockPosition[0];
            newBlockPosition[1] = blockPosition[1];
            if(newBlockPosition[0] == position[0] && newBlockPosition[1] == position[1]) {
                int[] currentPlayerPosition = PositionManager.getPlayerPosition();
                String direction = getDirection(currentPlayerPosition, position);
                switch (direction) {
                    case "U" -> newBlockPosition[1] = newBlockPosition[1] - 1;
                    case "D" -> newBlockPosition[1] = newBlockPosition[1] + 1;
                    case "L" -> newBlockPosition[0] = newBlockPosition[0] - 1;
                    case "R" -> newBlockPosition[0] = newBlockPosition[0] + 1;
                }
                if(!outOfBoundsCheck(newBlockPosition)) {
                    Tile tileToCheck = PositionManager.getTileAt(newBlockPosition);
                    return blockTileCheck(currentBlock, newBlockPosition, tileToCheck);
                } else {
                    System.out.println("Here");
                    return false;
                }

            }
        }
        return true;
    }

    public boolean blockTileCheck(Block block, int[] blockPosition, Tile tileToCheck) {
        if (tileToCheck instanceof Path) {
            PositionManager.setBlockPosition(block, blockPosition);
            return true;
        } else if (tileToCheck instanceof Button) {
            ((Button) tileToCheck).setIsPressed();
            PositionManager.setBlockPosition(block, blockPosition);
            return true;
        } else if (tileToCheck instanceof Water) {
            PositionManager.removeBlock(block);
            PositionManager.changeTile(new Path(), blockPosition);
            Board.getActors().removeBlock(block);
            return true;
        } else if (tileToCheck instanceof  Ice && !blockIsMoving) {
            blockIsMoving = true;
            iceMovement(block, PositionManager.getBlockPosition(block),
                    blockPosition);
            return true;
        }
        return false;
    }


    public static boolean outOfBoundsCheck(int[] currentPosition) {
        int positionX = currentPosition[0];
        int positionY = currentPosition[1];
        int[] size = BoardGUI.getBoardSize();
        int gridWidth = size[0];
        int gridHeight = size[1];
        return positionY < 0 || positionY > gridHeight - 1 || positionX < 0 ||
                positionX > gridWidth - 1;
    }

    public static boolean monsterOrBlockCheck(int[] positionToCheck) {
        HashMap<Block, int[]> blockHashMap = PositionManager.getBlockPosition();
        for(Map.Entry<Block, int[]> entry : blockHashMap.entrySet()) {
            int[] blockPosition = entry.getValue();
            if(blockPosition[0] == positionToCheck[0] && blockPosition[1] == positionToCheck[1]) {
                return true;
            }
        }
        ArrayList<Monster> monsterList = Board.getActors().getListOfMonsters();
        for (Monster monster : monsterList) {
            int[] monsterPosition = PositionManager.getMonsterPosition(monster);
            if (monsterPosition[0] == positionToCheck[0] && monsterPosition[1] == positionToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean tileCheck(int[] nextPosition) {
        Tile tileToCheck = PositionManager.getTileAt(nextPosition);
        if (tileToCheck instanceof Dirt) {
            PositionManager.changeTile(new Path(), nextPosition);
        } else if (tileToCheck instanceof Door) {
            doorCheck((Door) tileToCheck, nextPosition);
        } else if (tileToCheck instanceof Ice) {
            iceMovement("P", PositionManager.getPlayerPosition(), nextPosition);
            //Returns false as iceMovement sets the movement itself due to its
            //unique property and need for checking
            return false;
        } else if (tileToCheck instanceof Exit) {
            BoardGUI.setGameEnd("Exit");
        } else if (tileToCheck instanceof  Water) {
            BoardGUI.setGameEnd("Water");
        } else if (tileToCheck instanceof Button) {
            ((Button) tileToCheck).setIsPressed();
        } else if (tileToCheck instanceof ChipSocket) {
            chipSocketCheck((ChipSocket) tileToCheck, nextPosition);
        }
        return PositionManager.getTileAt(nextPosition).getCanMoveOn();
    }

    public boolean isBlockOnTile(int[] tileToCheck) {
        Actor blocks = Board.getActors();
        ArrayList<Block> listOfBlocks = blocks.getListOfBlocks();
        for(Block currentBlock : listOfBlocks) {
            int[] blockPosition = PositionManager.getBlockPosition(
                    currentBlock);
            if(blockPosition[0] == tileToCheck[0] &&
                    blockPosition[1] == tileToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    public void iceMovement(Object actorOnIce, int[] currentPosition, int[] nextPosition) {
        Ice iceTile = (Ice) PositionManager.getTileAt(nextPosition);
        String corner = iceTile.getCorner();
        String direction = getDirection(currentPosition, nextPosition);
        int[] icePosition = new int[2];
        icePosition[0] = nextPosition[0];
        icePosition[1] = nextPosition[1];
        nextPosition = findNextIceMovement(corner, direction, nextPosition);
        if(Objects.equals(actorOnIce, "P")) {
            registerPlayerIceMovement(currentPosition, icePosition, nextPosition);
        } else if (actorOnIce instanceof Block) {
            registerBlockIceMovement((Block) actorOnIce, currentPosition, icePosition, nextPosition);
        }
    }

    public void registerBlockIceMovement(Block block, int[] currentPosition,
                                         int[] icePosition, int[] nextPosition) {
        Tile tile = PositionManager.getTileAt(nextPosition);
        if (outOfBoundsCheck(nextPosition)) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, currentPosition);
        } else if (!(tile instanceof Path || tile instanceof Button || tile instanceof Trap ||
                tile instanceof Ice)) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, currentPosition);
        } else if (tile instanceof Ice) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, nextPosition);
        } else {
            blockIsMoving = false;
            blockTileCheck(block, nextPosition, PositionManager.getTileAt(nextPosition));
        }
    }

    public void registerPlayerIceMovement(int[] currentPosition, int[] icePosition, int[] nextPosition) {
        if (outOfBoundsCheck(nextPosition)) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
            BoardGUI.drawGame();
            iceMovement("P", icePosition, currentPosition);
        } else if (!PositionManager.getTileAt(nextPosition).getCanMoveOn() ||
                isBlockOnTile(nextPosition)) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
            BoardGUI.drawGame();
            iceMovement("P", icePosition, currentPosition);
        } else if (PositionManager.getTileAt(nextPosition) instanceof Ice) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
            BoardGUI.drawGame();
            iceMovement("P", icePosition, nextPosition);
        } else if (tileCheck(nextPosition) ){
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(nextPosition);
            BoardGUI.drawGame();
            blockCheck(nextPosition);
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

    public String getDirection(int[] currentPosition, int[] nextPosition) {
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

    public void chipSocketCheck(ChipSocket chipSocket, int[] tilePosition) {
        ArrayList<Item> itemList = Player.getPlayerItems();
        int playerChipCount = 0;
        for (Item item : itemList) {
            if (item instanceof Chip) {
                playerChipCount += 1;
            }
        }
        if (playerChipCount >= chipSocket.getNumberOfChips()) {
            PositionManager.changeTile(new Path(), tilePosition);
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
