import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board {
    private final Integer[] boardSize;
    private static final Actor BOARD_ACTORS = new Actor();
    private Tile tiles;
    private static final Position BOARD_POSITIONS = new Position();
    private static final Movement BOARD_MOVEMENT = new Movement();

    /**
     * Constructor for the board class
     * @param fileName String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String fileName) throws FileNotFoundException {
        File levelFile = new File(fileName);
        Scanner fileReader = new Scanner(levelFile);
        boardSize = new Integer[]{Integer.parseInt(fileReader.nextLine(),
                Integer.parseInt(fileReader.nextLine()))};
        //Two lines above get the first two lines from the file which are
        //The height and width of the board, and converts them to an Int
        while(fileReader.hasNextLine()) {
            createRow(fileReader.nextLine());
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
    private Actor getBoardActors(){
        return BOARD_ACTORS;
    }

    /**
     * Returns the board size as a Pair of Integers
     * @return returns a Pair object of two Integers,
     * representing the width and height of the board
     */
    private Integer[] getBoardSize(){
        return boardSize;
    }

    /**
     * Creates a row of the board
     * @param fileRow a line in a file that represents a row of the board and
     *                all objects that are on it
     */
    private void createRow(String fileRow) {
        Integer[] currentSquarePosition = new Integer[]{0,0};
        String[] rowElements = fileRow.split("_");
        for(int i = 0; i <= boardSize[0]; i++) {
            String[] propertiesOfSquare = rowElements[i].split("");
            createTile(propertiesOfSquare[0], currentSquarePosition);
            createActorOrItem(propertiesOfSquare[1], currentSquarePosition);
            currentSquarePosition[1] = currentSquarePosition[1]++;
        }
    }

    /**
    *Checks if what is on the square is an actor, item, or monster and calls
    *the respective method. This is seperate from the create createRow method 
    *for readability
    * @param actorOrItem String that represents either an actor or Item
    * @param currentSquarePosition the current position of the square we're checking
    */
    public void createActorOrItem(String actorOrItem, 
                                  Integer[] currentSquarePosition) {

    }

    /**
     * Creates a new tile for the current square of the board
     * @param tile tile to be created
     */
    private void createTile(String tile, Integer[] currentSquarePosition) {
    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     */
    private void createMonster(String monster, Integer[] currentSquarePosition) {
        switch (monster){
            case "F":
                BOARD_POSITIONS.setMonsterPosition(new Frog(),
                        currentSquarePosition);
            case "PB":
                BOARD_POSITIONS.setMonsterPosition(new PinkBall(),
                        currentSquarePosition);
            case "B":
                BOARD_POSITIONS.setMonsterPosition(new Bug(),
                        currentSquarePosition);
        }

    }

    /**
    * Creates an item on the board
    * @param item The item to be created
    * @param itemPositon The positon of the item being created on the board
    */
    private void createItem(String item, Integer[] itemPositon) {

    }

    /**
     * Creates a new player for the board
     * @param currentSquarePosition the positon of the player
     */
    private void createPlayer(Integer[] playerPositon) {
        BOARD_POSITIONS.setPlayerPosition(playerPositon);
    }
}
