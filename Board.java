import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board extends BoardGUI {
    private static final String LEVEL_FILE_PATH = "C:\\Users\\JayRo\\" +
            "Documents\\University\\" +
            "230\\A2_Code\\src\\main\\java\\Levels\\";
    private static final Movement BOARD_MOVEMENT = new Movement();
    private static final BoardGUI BOARD_GUI = new BoardGUI();
    private Tile[] tiles;


    /**
     * Constructor for the board class
     * @param levelFile String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String levelFile) {
        try {
            Scanner fileReader = new Scanner(new File(LEVEL_FILE_PATH + levelFile + ".txt"));
            BoardGUI.setBoardSize(new int[]{Integer.parseInt(fileReader.nextLine()),
                    Integer.parseInt(fileReader.nextLine())});
            int rowTracker = 0;
            while (fileReader.hasNextLine()) {
                createRow(fileReader.nextLine(), rowTracker);
                rowTracker++;
            }
            System.out.println(PositionManager.tilePosition.toString());
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

    /**
     * Returns the actor object that contains the actors of the board
     * @return returns an Actor
     */
    //private Actor getBoardActors(){
     //   return BOARD_ACTORS;
   // }


    /**
     * Creates a row of the board
     * @param fileRow a line in a file that represents a row of the board and
     *                all objects that are on it
     */
    private void createRow(String fileRow, int nextRow) {
        String[] rowElements = fileRow.split("_");
        for(int i = 0; i <= BoardGUI.getBoardSize()[0] - 1; i++) {
            int[] currentSquarePosition = new int[]{i,nextRow};
            String[] propertiesOfSquare = rowElements[i].split("");
            createTile(propertiesOfSquare[0], currentSquarePosition);
            if(rowElements[i].length() > 1) {
                createActorOrItem(rowElements[i], currentSquarePosition);
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
        switch (toCheck[1]) {
            case "M":
                createMonster(toCheck, position);
        }

    }

    /**
     * Creates a new tile for the current square of the board
     * @param tile tile to be created
     */
    private void createTile(String tile, int[] tilePosition) {
        switch (tile) {
            case "P":
                PositionManager.setNewTile(new Path(), tilePosition);
                break;
            case "D":
                PositionManager.setNewTile(new Dirt(), tilePosition);
                break;
        }
    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     */
    private void createMonster(String[] monster, int[] monsterPosition) {
        String monsterToFind = monster[2];
        if(monster.length == 5) {
            monsterToFind = monster[2] + monster[3];
        }
        switch (monsterToFind){
            case "F":
                PositionManager.setMonsterPosition(new Frog(),
                        monsterPosition);
            case "PB":
                PositionManager.setMonsterPosition(new PinkBall(monster[4]),
                        monsterPosition);
            case "B":
                PositionManager.setMonsterPosition(new Bug(),
                        monsterPosition);
        }

    }

    /**
    * Creates an item on the board
    * @param item The item to be created
    * @param itemPosition The position of the item being created on the board
    */
    private void createItem(String item, int[] itemPosition) {

    }

    /**
     * Creates a new player for the board
     * @param playerPosition the position of the player
     */
    private void createPlayer(int[] playerPosition) {
        PositionManager.setPlayerPosition(playerPosition[0], playerPosition[1]);
    }

}
