import java.util.*;

/**
 * Class that handles the movement for the Player and Block, as well as checks
 * for if a movement is valid
 * @author Jack Rogers
 * @version 1.5
 */
public class Movement {
    //Flag for if a block is moving or not
    private boolean blockIsMoving = false;

    /**
     * Performs the next movement of a player, it checks if the move is valid
     * before updating the player's position
     * @param nextMove the next move of the player based off their keyboard
     *                 input
     */
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
        if (!outOfBoundsCheck(newPosition) &&
                tileCheck(newPosition) && trapCheck(playerPosition)) {
            if (itemCheck(newPosition)) {
                getItemForPlayer(newPosition);
            }
            if (blockCheck(newPosition)) {
                PositionManager.setPlayerPosition(newPosition);
                blockDeathCheck();
            }
        }
    }

    /**
     * Checks if a trap at a position is released or not
     * @param position position being checked
     * @return whether the trap is released or not
     */
    public boolean trapCheck(int[] position) {
        if (PositionManager.getTileAt(position) instanceof Trap) {
            return ((Trap) PositionManager.getTileAt(position)).
                    getIsReleased();
        }
        return true;
    }

    /**
     * Check whether the block is on the same tile as the player, and if so,
     * calls for the game to end
     */
    public void blockDeathCheck() {
        int[] playerPosition = PositionManager.getPlayerPosition();
        Actor blocks = Board.getActors();
        ArrayList<Block> listOfBlocks = blocks.getListOfBlocks();
        for (Block currentBlock : listOfBlocks) {
            int[] blockPosition = PositionManager.getBlockPosition(
                    currentBlock);
            if (blockPosition[0] == playerPosition[0] && blockPosition[1] ==
                    playerPosition[1]) {
                BoardGUI.setGameEnd("Block");
            }
        }
    }

    /**
     * Checks whether the movement of the block is a valid one, finds the
     * block being moved, then checks if the movement being done is a valid one
     * @param position the position being checked
     * @return if the movement is valid
     */
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
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * Check if the tile the block is being moved onto is one it is allowed to
     * be moved onto
     * @param block block being moved
     * @param blockPosition position of the block
     * @param tileToCheck tile being checked
     * @return if the movement is valid
     */
    public boolean blockTileCheck(Block block, int[] blockPosition,
                                  Tile tileToCheck) {
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

    /**
     * Check if a movement takes an actor out of bounds
     * @param currentPosition position being checked
     * @return if the position is out of bounds
     */
    public static boolean outOfBoundsCheck(int[] currentPosition) {
        int positionX = currentPosition[0];
        int positionY = currentPosition[1];
        int[] size = BoardGUI.getBoardSize();
        int gridWidth = size[0];
        int gridHeight = size[1];
        return positionY < 0 || positionY > gridHeight - 1 || positionX < 0 ||
                positionX > gridWidth - 1;
    }

    /**
     * Check if a monster or block is on the tile being checked
     * @param positionToCheck position being checked
     * @return if a monster or block is on the position
     */
    public static boolean monsterOrBlockCheck(int[] positionToCheck) {
        HashMap<Block, int[]> blockHashMap = PositionManager.
                getBlockPosition();
        for (Map.Entry<Block, int[]> entry : blockHashMap.entrySet()) {
            int[] blockPosition = entry.getValue();
            if (blockPosition[0] == positionToCheck[0] && blockPosition[1] ==
                    positionToCheck[1]) {
                return true;
            }
        }
        ArrayList<Monster> monsterList = Board.getActors().getListOfMonsters();
        for (Monster monster : monsterList) {
            int[] monsterPosition = PositionManager.getMonsterPosition(
                    monster);
            if (monsterPosition[0] == positionToCheck[0] &&
                    monsterPosition[1] == positionToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for tile whether a player can move onto a tile and any
     * interactions for when a player moves
     * @param nextPosition where player is moving to
     * @return if a move onto a tile is valid
     */
    public boolean tileCheck(int[] nextPosition) {
        Tile tileToCheck = PositionManager.getTileAt(nextPosition);
        if (tileToCheck instanceof Dirt) {
            PositionManager.changeTile(new Path(), nextPosition);
        } else if (tileToCheck instanceof Door) {
            doorCheck((Door) tileToCheck, nextPosition);
        } else if (tileToCheck instanceof Ice) {
            iceMovement("P", PositionManager.getPlayerPosition(),
                    nextPosition);
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

    /**
     * Check if a block is on a tile
     * @param tileToCheck tile being checked
     * @return whether a block is on it
     */
    public boolean isBlockOnTile(int[] tileToCheck) {
        Actor blocks = Board.getActors();
        ArrayList<Block> listOfBlocks = blocks.getListOfBlocks();
        for (Block currentBlock : listOfBlocks) {
            int[] blockPosition = PositionManager.getBlockPosition(
                    currentBlock);
            if (blockPosition[0] == tileToCheck[0] &&
                    blockPosition[1] == tileToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles moving an actor along ice
     * @param actorOnIce Player or Block on the ice
     * @param currentPosition current position of the actor
     * @param nextPosition position actor is being to
     */
    public void iceMovement(Object actorOnIce, int[] currentPosition,
                            int[] nextPosition) {
        if(PositionManager.getTileAt(nextPosition) instanceof Ice) {
            Ice iceTile = (Ice) PositionManager.getTileAt(nextPosition);
            String corner = iceTile.getCorner();
            String direction = getDirection(currentPosition, nextPosition);
            int[] icePosition = new int[2];
            icePosition[0] = nextPosition[0];
            icePosition[1] = nextPosition[1];
            nextPosition = findNextIceMovement(corner, direction, nextPosition);
            if (Objects.equals(actorOnIce, "P")) {
                registerPlayerIceMovement(currentPosition, icePosition,
                        nextPosition);
            } else if (actorOnIce instanceof Block) {
                registerBlockIceMovement((Block) actorOnIce, currentPosition,
                        icePosition, nextPosition);
            }
        } else {
            if(actorOnIce == "P") {
                PositionManager.setPlayerPosition(nextPosition);
            } else {
                PositionManager.setBlockPosition((Block) actorOnIce, nextPosition);
            }
        }
    }

    /**
     * Registers a block's movement over ice, and any further ice movement or
     * interactions that may take place
     * @param block Block moving over ice
     * @param currentPosition current position
     * @param icePosition position of the ice tile
     * @param nextPosition next position to move to
     */
    public void registerBlockIceMovement(Block block, int[] currentPosition,
                                         int[] icePosition,
                                         int[] nextPosition) {
        Tile tile = PositionManager.getTileAt(nextPosition);
        if (outOfBoundsCheck(nextPosition)) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, currentPosition);
        } else if (!(tile instanceof Path || tile instanceof Button ||
                tile instanceof Trap ||
                tile instanceof Ice)) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, currentPosition);
        } else if (tile instanceof Ice) {
            PositionManager.setBlockPosition(block, icePosition);
            iceMovement(block, icePosition, nextPosition);
        } else {
            blockIsMoving = false;
            blockTileCheck(block, nextPosition, PositionManager.
                    getTileAt(nextPosition));
        }
    }

    /**
     * Perform a Player's movement onto ice, and any further movement or
     * interactions that may take place
     * @param currentPosition Current position of the Player
     * @param icePosition position of the ice tile being moved onto
     * @param nextPosition position being moved onto off the ice tile
     */
    public void registerPlayerIceMovement(int[] currentPosition, int[] icePosition, int[] nextPosition) {
        if (outOfBoundsCheck(nextPosition)) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
            iceMovement("P", icePosition, currentPosition);
        } else if (!PositionManager.getTileAt(nextPosition).getCanMoveOn() ||
                isBlockOnTile(nextPosition)) {
            PositionManager.setPlayerPosition(icePosition);
            if (blockCheck(nextPosition)) {
                PositionManager.setPlayerPosition(nextPosition);
            } else {
                iceItemCheck(icePosition);
                PositionManager.setPlayerPosition(icePosition);
                iceMovement("P", icePosition, currentPosition);
            }
        } else if (PositionManager.getTileAt(nextPosition) instanceof Ice) {
            iceItemCheck(icePosition);
            PositionManager.setPlayerPosition(icePosition);
            iceMovement("P", icePosition, nextPosition);
        } else if (tileCheck(nextPosition) ){
            PositionManager.setPlayerPosition(nextPosition);
            if(itemCheck(nextPosition)) {
                getItemForPlayer(nextPosition);
            }
            blockCheck(nextPosition);
        }
    }

    /**
     * Finds the next ice movement depending on the direction an actor is
     * travelling in
     * @param corner corner of the ice tile
     * @param direction direction of the actor
     * @param nextPosition position from being on the ice tile
     * @return the position after moving over the ice tile
     */
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

    /**
     * Check if an item is on the ice tile being moved over
     * @param icePosition position of the ice tile
     */
    public void iceItemCheck(int[] icePosition) {
        if (itemCheck(icePosition)) {
            getItemForPlayer(icePosition);
        }
    }

    /**
     * Gets the direction the actor is moving in
     * @param currentPosition current position of the actor
     * @param nextPosition position acting is moving to
     * @return direction the actor is travelling in
     */
    public String getDirection(int[] currentPosition, int[] nextPosition) {
        if (nextPosition[0] - currentPosition[0] == -1) {
            return "L";
        } else if (nextPosition[0] - currentPosition[0] == 1) {
            return "R";
        } else if (nextPosition[1] - currentPosition[1] == -1) {
            return "U";
        } else {
            return "D";
        }
    }

    /**
     * Checks if the player has an item to unlock a door
     * @param doorToCheck Door being checked
     * @param nextPosition Position of the door
     */
    public void doorCheck(Door doorToCheck, int[] nextPosition) {
        ArrayList<Item> itemList = Player.getPlayerItems();
        boolean keepChecking = true;
        int i = 0;
        while (i < itemList.size() - 1 && keepChecking) {
            if (itemList.get(i) instanceof Key newKey) {
                if (Objects.equals(newKey.getColour(),
                        doorToCheck.getColour())) {
                    PositionManager.changeTile(new Path(), nextPosition);
                    Player.removeItem(itemList.get(i));
                    keepChecking = false;
                }
            }
        }
    }

    /**
     * Checks if the player has enough chips to open a chipSocket
     * @param chipSocket chipSocket being checked
     * @param tilePosition Position of the chipSocket
     */
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

    /**
     * Check if an item is on the tile being moved onto by the player
     * @param positionToCheck position being checked
     * @return whether an item is on the tile or not
     */
    public boolean itemCheck(int[] positionToCheck) {
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for (Map.Entry<Item, int[]> entry : itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if (itemPosition[0] == positionToCheck[0] && itemPosition[1] ==
                    positionToCheck[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the item at the tile position to the Player's inventory
     * @param itemToGetPosition position with item on it
     */
    public void getItemForPlayer(int[] itemToGetPosition) {
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for (Map.Entry<Item, int[]> entry : itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if (itemPosition[0] == itemToGetPosition[0] && itemPosition[1] ==
                    itemToGetPosition[1]) {
                Player.addPlayerItems(entry.getKey());
                PositionManager.removeItem(entry.getKey());
                break;
            }
        }
    }
}
