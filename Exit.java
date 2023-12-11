/**
 * Handles the Exit tile and it's related properties
 * @author Dan Cross
 * @author Jack Jones
 * @version 1.0
 */
public class Exit extends Tile {
  //Whether the tile can be moved on or not
  private static final boolean CAN_MOVE_ON = true;
  //File name containing the image for the exit tile
  private static final String IMAGE_FILE = "Exit.png";

  /**
   * Returns whether the tile can be moved on or not
   * @return if the tile is able to be moved on by the player
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the file name that contains the image of the exit tile
   * @return String file name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
