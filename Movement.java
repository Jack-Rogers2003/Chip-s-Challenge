/**
 * A class to handle the movement of all actor classes
 * All actor positions passed through the actor class rather than position class
 *
 * @author Dan Cross, Jack Jones
 * @version 1.0
 * 
 */
public class Movement {

    private Actor movingActor;

    public Movement(Actor movingActor){
        this.movingActor = movingActor;
    }

    /**
     * Method to change a given actors position if possible
     *
     * @param desiredPosition Integer[] is the position the moving actor wants to move to
     */
    public void moveToPosition(Integer[] desiredPosition) {
        if (canMove( desiredPosition) == false) {
            desiredPosition = movingActor.getPosition()
        }
        movingActor.setPosition(desiredPosition);
    }

    /**
     * Method to check if a given positon will accept the actor
     *
     * @param desiredPosition Integer[] x,y value of intended next position
     * @return boolean value that if true states a player can move to given position
     */
    private Boolean canMove(Integer[] desiredPosition) {
       if (acceptTile(desiredPosition) == true && isOutOfBounds(desiredPosition, board) == false) {
           return true;
       }
    }
    /**
     * Method to check if given position has a tile appropriate for movement
     *
     * @param desiredPosition Integer[] x,y value of intended next position
     * @return boolean value that if true states a tile is acceptable
     */
    private Boolean acceptTile(Integer[] desiredPosition) {
        Tile tile = Position.getTileAtPosition(desiredPosition);
        for (int i = 0; i < tile.getMoveability().length; i++) {
            if (tile.getMoveability()[i] == this.movingActor ) {
                return true;
            } else return false;
        }
    }

    /** 
     * Checks if given position is out of bounds of the board in play
     *
     * @param desiredPosition Integer[] x,y value of intended next position 
     * @param board Board value of current board being played on
     * @return boolean value of whether the position is OOB
     */
    private boolean isOutOfBounds(Integer[] desiredPosition, Board board) {
        Integer[] boardSize = board.getBoardSize();
        boolean xOOB = (desiredPosition[0] > boardSize[0]) || (desiredPosition[0] < (boardSize[0] - boardSize[0]));
        boolean yOOB = (desiredPosition[1] > boardSize[1]) || (desiredPosition[1] < (boardSize[1] - boardSize[1]));
        if (xOOB || yOOB) {
            return true;
        } else {
            return false;
        }
    }


}
