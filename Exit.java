/**
 * This class represents the Exit tile and has methods to check how an actor may
 * or may not interact with it
 * @author Benji Brew, Jack Rogers
 * @version 1.1
 */

public class Exit extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Exit.png";

  /**
   * Method that checks if an actor can move onto an exit tile
   *
   * @return the boolean value of CAN_MOVE_ON
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Method that retrieves the image on an exit tile
   *
   * @return the image file
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
