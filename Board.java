import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board extends BoardGUI {
    private static final Position BOARD_POSITIONS = new Position();
    private static final Movement BOARD_MOVEMENT = new Movement();
    private static final BoardGUI BOARD_GUI = new BoardGUI();
    private final Integer[] boardSize;
    private Tile[] tiles;
    private Timeline tickTimeline;
    private Integer currentTick;

    /**
     * Constructor for the board class
     * @param levelFile String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(File levelFile) throws FileNotFoundException {
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
     * Adds a tick every half a second
     */
    public void tick() {
        currentTick += 1;
    }

    public String test() {
        return "You can do stuff after the window is gone";
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
    *the respective method. This is separate from the create createRow method
    *for readability
    * @param actorOrItem String that represents either an actor or Item
    * @param actorOrItemPosition position of the actor or item to be created
    */
    public void createActorOrItem(String actorOrItem,
                                  Integer[] actorOrItemPosition) {

    }

    /**
     * Creates a new tile for the current square of the board
     * @param tile tile to be created
     */
    private void createTile(String tile, Integer[] tilePosition) {

    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     */
    private void createMonster(String monster, Integer[] monsterPosition) {
        switch (monster){
            case "F":
                BOARD_POSITIONS.setMonsterPosition(new Frog(),
                        monsterPosition);
            case "PB":
                BOARD_POSITIONS.setMonsterPosition(new PinkBall(),
                        monsterPosition);
            case "B":
                BOARD_POSITIONS.setMonsterPosition(new Bug(),
                        monsterPosition);
        }

    }

    /**
    * Creates an item on the board
    * @param item The item to be created
    * @param itemPosition The position of the item being created on the board
    */
    private void createItem(String item, Integer[] itemPosition) {

    }

    /**
     * Creates a new player for the board
     * @param playerPosition the position of the player
     */
    private void createPlayer(Integer[] playerPosition) {
        BOARD_POSITIONS.setPlayerPosition(playerPosition);
    }

}
