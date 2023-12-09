/**
 * This class represents the Exit tile and has methods to check how an actor may
 * or may not interact with it
 * @author Benji Brew, Jack Rogers
 * @version 1.1
 */

public class Exit extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Exit.png";

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return IMAGE_FILE;
  }
}
