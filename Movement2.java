/* Class that handles movement between actor subclasses
 * @author Jack Jones
 * @version 1.0
 * 
 */

public class Movement {

 /* Calculates player movement from player's input
 *
 * @param direction String value of keylistener input, dictates direction
 */
    public void playerMovement(String direction) { //direction from keylistener
        Integer[] playerPos = Position.getPlayerPosition();
        switch(direction)
        {
            case ("A"): //Left
                playerPos[0] = playerPos[0] - 1;
                break;
            case ("D"): //Right
                playerPos[0] = playerPos[0] + 1;
                break;
            case ("W"): //Up
                playerPos[1] = playerPos[1] + 1;
                break;
            case ("S"): //Down
                playerPos[1] = playerPos[1] - 1;
                break;
        }
    }

 /* Moves the actor to a new position
 *
 * @param newPos Integer[] value of new position 
 * @param actor Actor value of the actor being moved
 * @param board Board value of current board being played on
 */
    public void moveActor(Integer[] newPos, Actor actor, Board board) {
        if (isOutOfBounds(board.getBoardSize(), board)) {
        } else {
            Position newPosCLass = null;
            newPosCLass.setPlayerPosition(newPos);  //No getActorPos Integer[] so using Player Integer[] rn
            actor.setPosition(newPosCLass);
        }
    }


/* Checks if a position (the new position) is out of bounds of the board in play
 *
 * @param newPos Integer[] value of new position 
 * @param board Board value of current board being played on
 * @return boolean value of whether the position is OOB
 */
    private boolean isOutOfBounds(Integer[] newPos, Board board) {
        Integer[] boardSize = board.getBoardSize();
        boolean xOOB = (newPos[0] > boardSize[0]) || (newPos[0] < (boardSize[0] - boardSize[0]));
        boolean yOOB = (newPos[1] > boardSize[1]) || (newPos[1] < (boardSize[1] - boardSize[1]));
        if (xOOB || yOOB) {
            return true;
        } else {
            return false;
        }
    }

 /* Calculates monster movement from monster AI
 *
 */
    public void monsterMovement(Monster monster, Integer[] move) {
        //TBC(?)
    }


}
