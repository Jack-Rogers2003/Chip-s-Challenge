import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board {
    //Current directory we're operating in, used to find folders with game
    //details
    private static final String CURRENT_DIRECTORY = System.getProperty(
            "user.dir");
    //The file path for where the game's levels are stored
    private static final String LEVEL_FILE_PATH = CURRENT_DIRECTORY +
            "\\Levels\\";
    //directory for where saved games are stored
    private static final File SAVED_GAMES_PATH = new File(
            CURRENT_DIRECTORY + "\\saves\\");
    //Length a pink ball monster will be when reading a file
    private static final int PINK_BALL_LENGTH = 4;
    //Position of the PinkBall's direction when reading a file
    private static final int PINK_BALL_DIRECTION_POSITION = 3;
    //Actor object that tracks all actors on the board
    private static final Actor ACTOR = new Actor();
    //ArrayList to track any button tiles that haven't been assigned a trap
    private final ArrayList<Button> waitingButton = new ArrayList<>();
    //Arraylist to track any Trap tiles that haven't been assigned a button
    private final ArrayList<Trap> waitingTrap = new ArrayList<>();
    //Boolean for if we should load a file from the saved games directory

    /**
     * Constructor for the board class
     * @param levelFile String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String levelFile) {
        try {
            Player.resetItems();
            PositionManager.resetPositions();
            ACTOR.reset();
            Scanner fileReader;
            //Check for if we are loading from saved or loading from the
            //levels directory
            if (!Objects.equals(levelFile, "L")) {
                fileReader = new Scanner(new File(LEVEL_FILE_PATH
                        + levelFile + ".txt"));
            } else {
                fileReader = new Scanner(new File(SAVED_GAMES_PATH +
                        "\\" + GameGUIManager.getCurrentProfile() + ".txt"));
                GameGUIManager.setCurrentLevel(fileReader.nextLine());
                loadPlayerInventory(fileReader.nextLine());
            }
            BoardGUI.setBoardSize(new int[]{Integer.parseInt(fileReader.
                    nextLine()),
                    Integer.parseInt(fileReader.nextLine())});
            BoardGUI.setGameTime(Integer.parseInt(fileReader.nextLine()));
            int rowTracker = 0;
            while (fileReader.hasNextLine()) {
                createRow(fileReader.nextLine(), rowTracker);
                rowTracker++;
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When loading a game from the saved directory, loads any items the player
     * may have had at the time of the save
     * @param items The items the player had at the time of the game being
     *              saved
     */
    private void loadPlayerInventory(String items) {
        String[] listOfItems = items.split("_");
        for (String listOfItem : listOfItems) {
            createItem(listOfItem.split(""));
        }
    }

    /**
     * Returns the Actor object for the board that contains all the actors for
     * the board
     * @return Actor object of the board
     */
    public static Actor getActors() {
        return ACTOR;
    }

    /**
     * Creates a row of the board
     * @param fileRow a line in a file that represents a row of the board and
     *                all objects that are on it
     * @param nextRow the next row to be created
     */
    private void createRow(String fileRow, int nextRow) {
        String[] rowElements = fileRow.split("_");
        for (int i = 0; i <= BoardGUI.getBoardSize()[0] - 1; i++) {
            int[] currentSquarePosition = new int[]{i, nextRow};
            String[] propertiesOfSquare = rowElements[i].split(";");
            createTile(propertiesOfSquare[0], currentSquarePosition);
            if (rowElements[i].length() > 1) {
                for (int j = 1; j < propertiesOfSquare.length; j++) {
                    createActorOrItem(propertiesOfSquare[j],
                            currentSquarePosition);
                }
            }
        }
    }

    /**
    *Checks if what is on the square is an actor, item, or monster and calls
    *the respective method. This is separate from the create createRow method
    *for readability
    * @param actorOrItem String that represents either an actor or Item
    * @param position position of the actor or item to be created
    */
    public void createActorOrItem(String actorOrItem, int[] position) {
        String[] toCheck = actorOrItem.split("");
        switch (toCheck[0]) {
            case "M" -> createMonster(toCheck, position);
            case "P" -> PositionManager.setPlayerPosition(position);
            case "I" -> createItem(toCheck, position);
            case "B" -> createBlock(position);
        }
    }

    /**
     * Creates a block object for the board, adds it into the Actor object, and
     * sets its position in PositionManager
     * @param position position of the block being added
     */
    public void createBlock(int[] position) {
        Block block = new Block();
        ACTOR.setNewBlock(block);
        PositionManager.setBlockPosition(block, position);
    }

    /**
     * Creates a new tile for the current square of the board
     * @param tile tile to be created
     * @param tilePosition position the tile to be created
     */
    private void createTile(String tile, int[] tilePosition) {
        String[] tileToCheck = tile.split("");
        tile = tileToCheck[0];
        switch (tile) {
            case "P" -> PositionManager.setNewTile(new Path(), tilePosition);
            case "D" -> PositionManager.setNewTile(new Dirt(), tilePosition);
            case "W" -> PositionManager.setNewTile(new Wall(), tilePosition);
            case "A" -> PositionManager.setNewTile(new Water(), tilePosition);
            case "E" -> PositionManager.setNewTile(new Exit(), tilePosition);
            case "I" -> PositionManager.setNewTile(new Ice(tileToCheck[1]),
                    tilePosition);
            case "O" -> PositionManager.setNewTile(new Door(tileToCheck[1]),
                    tilePosition);
            case "C" -> PositionManager.setNewTile(new ChipSocket(Integer.
                    parseInt(tileToCheck[1])), tilePosition);
            case "B" -> {
                Button buttonToAdd = new Button();
                trapWaitingToAttach(buttonToAdd);
                PositionManager.setNewTile(buttonToAdd, tilePosition);
            }
            case "T" -> {
                Trap trapToAttach = new Trap();
                buttonWaitingToAttach(trapToAttach);
                PositionManager.setNewTile(trapToAttach, tilePosition);
            }
        }
    }

    /**
     * Checks to see if any buttons are in the waitingButton ArrayList, if so,
     * it connects the button at the front of the list ad the trap. If there
     * aren't any buttons waiting, then it adds the trap to the waitingTrap
     * ArrayList
     * @param trapToAttach trap being created and needing button to attach to
     */
    public void buttonWaitingToAttach(Trap trapToAttach) {
        if (waitingButton.size() > 0) {
            trapToAttach.setConnectedButton(waitingButton.get(0));
            waitingButton.remove(0);
        } else {
            waitingTrap.add(trapToAttach);
        }
    }

    /**
     * Checks to see if any traps are in the waitingTraps ArrayList, if so,
     * it connects the trap at the front of the list and the button. If there
     * aren't any traps waiting, then it button the trap to the waitingButton
     * ArrayList
     * @param buttonToAttach button looking for trap to attach to
     */
    public void trapWaitingToAttach(Button buttonToAttach) {
        if (waitingTrap.size() > 0) {
            waitingTrap.get(0).setConnectedButton(buttonToAttach);
            waitingTrap.remove(0);
        } else {
            waitingButton.add(buttonToAttach);
        }
    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     * @param monsterPosition position of the monster being created
     */
    private void createMonster(String[] monster, int[] monsterPosition) {
        String monsterToFind = monster[1];
        if (monster.length == PINK_BALL_LENGTH) {
            monsterToFind = monster[1] + monster[2];
        }
        switch (monsterToFind) {
            case "F" -> {
                Frog newFrog = new Frog();
                ACTOR.setNewMonster(newFrog);
                PositionManager.setMonsterPosition(newFrog, monsterPosition);
            }
            case "PB" -> {
                PinkBall newBall = new PinkBall(monster[
                        PINK_BALL_DIRECTION_POSITION]);
                ACTOR.setNewMonster(newBall);
                PositionManager.setMonsterPosition(newBall, monsterPosition);
            }
            case "B" -> {
                Bug newBug = new Bug(monster[2]);
                ACTOR.setNewMonster(newBug);
                PositionManager.setMonsterPosition(newBug, monsterPosition);
            }
        }
    }

    /**
    * Creates an item on the board
    * @param item The item to be created
    * @param itemPosition The position of the item being created on the board
    */
    private void createItem(String[] item, int[] itemPosition) {
        String itemToFind = item[1];
        switch (itemToFind) {
            case "C" -> PositionManager.setItemPosition(new Chip(),
                    itemPosition);
            case "K" -> PositionManager.setItemPosition(new Key(item[2]),
                    itemPosition);
        }
    }

    /**
     * This an overloading of the createItem method that only passes through an
     * array of Strings of items. This is used when loading a level from a
     * saved game and this checks through the list, and adds t the Player's
     * items
     * @param items An array of String Items the player had when the game was
     *              saved
     */
    public static void createItem(String[] items) {
        String itemToFind = items[0];
        switch (itemToFind) {
            case "C" -> Player.addPlayerItems(new Chip());
            case "K" -> Player.addPlayerItems(new Key(items[1]));
        }
    }

}