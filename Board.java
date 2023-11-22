import javafx.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.0
 */
public class Board {
    private Integer[] boardSize;
    private Actor boardActors;
    private Tile tiles;
    private Position boardPositions;
    private Integer[] currentSquarePosition;

    /**
     * Constructor for the board class
     * @param fileName String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String fileName) throws FileNotFoundException {
        File levelFile = new File(fileName);
        Scanner fileReader = new Scanner(levelFile);
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
        return boardActors;
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
        String[] rowElements = fileRow.split("_");
        for(int i = 0; i <= rowElements.length; i++) {
            String[] currentSquare = rowElements[i].split("");
            createTile(currentSquare[0]);

        }
    }

    private void createTile(String tile) {

    }

    /**
     * Creates a new monster on the board
     * @param monster The monster to be created
     */
    private void createMonster(String monster) {
        switch (monster){
            case "F":
                boardPositions.setMonsterPosition(new Frog(),
                        currentSquarePosition);
            case "PB":
                boardPositions.setMonsterPosition(new PinkBall(),
                        currentSquarePosition);
            case "B":
                boardPositions.setMonsterPosition(new Bug(),
                        currentSquarePosition);
        }

    }

    private void createItem(String item) {

    }

    /**
     * Creates a new player for the board
     */
    private void createPlayer() {
        boardPositions.setPlayerPosition(currentSquarePosition);
    }
}
