/* A class to handle the movement of all actor classes
 * All actor positions passed through the actor class rather than position class
 * @author Dan Cross
 * @version 1.0
 * 
 * side note - javadoc not finished and my implementation of this class requires some tinkering
 * with other classes (some of which i've done whereas some still needs doing)
 */
public class Movement {

    private Actor movingActor;

    public Movement(Actor movingActor){
        this.movingActor = movingActor;
    }


    public void moveToPosition(Integer[] desiredPosition) {
        if (canMove( desiredPosition) == false) {
            //cannot move
        } else {
            movingActor.setPosition(desiredPosition);
        }
    }

    public Boolean canMove(Integer[] desiredPosition) {
        //if actor can move on tile
        Tile tile = Position.getTileAtPosition(desiredPosition);
        for (int i = 0; i < tile.getMoveability().length; i++) {
            if (tile.getMoveability()[i] == this.movingActor ) {
                return true;
            } else return false;
        }
    }

}
