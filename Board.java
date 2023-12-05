import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board extends BoardGUI {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String LEVEL_FILE_PATH = CURRENT_DIRECTORY +
            "\\src\\main\\java\\Levels\\";
    private static final Movement BOARD_MOVEMENT = new Movement();
    private final Actor actors = new Actor();
    private final ArrayList<Button> waitingButton = new ArrayList<>();
    private final ArrayList<Trap> waitingTrap = new ArrayList<>();


    /**
     * Constructor for the board class
     * @param levelFile String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String levelFile) {
        try {
            Scanner fileReader = new Scanner(new File(LEVEL_FILE_PATH
                    + levelFile + ".txt"));
            BoardGUI.setBoardSize(new int[]{Integer.parseInt(fileReader.nextLine()),
                    Integer.parseInt(fileReader.nextLine())});
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
     * Calls for the movement of a player
     */
    private void getPlayerMovement(){

    }

    public Actor getActors() {
        return actors;
    }

    /**
     * Creates a row of the board
     * @param fileRow a line in a file that represents a row of the board and
     *                all objects that are on it
     */
    private void createRow(String fileRow, int nextRow) {
        String[] rowElements = fileRow.split("_");
        for(int i = 0; i <= BoardGUI.getBoardSize()[0] - 1; i++) {
            int[] currentSquarePosition = new int[]{i,nextRow};
            String[] propertiesOfSquare = rowElements[i].split(";");
            createTile(propertiesOfSquare[0], currentSquarePosition);
            if(rowElements[i].length() > 1) {
                for(int j = 1; j < propertiesOfSquare.length; j++) {
                    createActorOrItem(propertiesOfSquare[j], currentSquarePosition);
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

    public void createBlock(int[] position) {
        Block block = new Block();
        actors.setNewBlock(block);
        PositionManager.setBlockPosition(block, position);
    }

    /**
     * Creates a new tile for the current square of the board
     * @param tile tile to be created
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
            case "I" -> PositionManager.setNewTile(new Ice(tileToCheck[1]), tilePosition);
            case "O" -> PositionManager.setNewTile(new Door(tileToCheck[1]), tilePosition);
            case "C" -> PositionManager.setNewTile(new ChipSocket(1), tilePosition);
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

    public void buttonWaitingToAttach(Trap trapToAttach) {
        if(waitingButton.size() > 0) {
            trapToAttach.setConnectedButton(waitingButton.get(0));
            waitingButton.remove(0);
        } else {
            waitingTrap.add(trapToAttach);
        }
    }

    public void trapWaitingToAttach(Button buttonToAttach) {
        if(waitingTrap.size() > 0) {
            waitingTrap.get(0).setConnectedButton(buttonToAttach);
            waitingTrap.remove(0);
        } else {
            waitingButton.add(buttonToAttach);
        }
    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     */
    private void createMonster(String[] monster, int[] monsterPosition) {
        String monsterToFind = monster[1];
        if(monster.length == 4) {
            monsterToFind = monster[1] + monster[2];
        }
        switch (monsterToFind) {
            case "F" -> {
                Frog newFrog = new Frog();
                actors.setNewMonster(newFrog);
                PositionManager.setMonsterPosition(newFrog, monsterPosition);
            }
            case "PB" -> {
                PinkBall newBall = new PinkBall(monster[3]);
                actors.setNewMonster(newBall);
                PositionManager.setMonsterPosition(newBall, monsterPosition);
            }
            case "B" -> {
                Bug newBug = new Bug(monster[2]);
                actors.setNewMonster(newBug);
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
            case "C" -> PositionManager.setItemPosition(new Chip(), itemPosition);
            case "K" -> PositionManager.setItemPosition(new Key(item[2]), itemPosition);
        }
    }

}