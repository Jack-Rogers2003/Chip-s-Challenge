import javafx.util.Pair;

/**
 * Class that handles generating the board and connects elements of it
 * @author Jack Rogers
 * @version 1.o
 */
public class Board {
    private Pair<Integer, Integer> boardSize;
    private Actor boardActors;
    private Tile tiles;

    /**
     * Constructor for the board class
     * @param fileName String input that is the name of the file that contains
     *                 the details of the current level
     */
    public Board(String fileName) {

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
    private Pair<Integer, Integer> getBoardSize(){
        return boardSize;
    }
}
