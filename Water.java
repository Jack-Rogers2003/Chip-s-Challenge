/**
 * This class creates the Water tile and handles any related properties
 * and operations
 * @author Jack Jones
 * @author Dan Cross
 */

public class Water extends Tile {
  //Whether the tile can be moved on or not
  private static final boolean CAN_MOVE_ON = true;
  //Name of the file containing the image of the Water tile
  private static final String IMAGE_FILE = "water.png";

  /**
   * Returns that the water tile can be moved on
   * @return the boolean of if it can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the path to the image of the Water tile
   * @return String file path of the image
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
