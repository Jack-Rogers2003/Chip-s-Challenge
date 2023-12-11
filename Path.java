/**
 * Class that handles the Path tile, and it's related properties and operations
 * @author Jack Jones
 * @author Dan Cross
 * @version 1.1
 */
public class Path extends Tile {
  //Whether the tile can be moved on
  private static final boolean CAN_MOVE_ON = true;
  //Name of the file containing the image of the Path tile
  private static final String IMAGE_FILE = "Path.png";

  /**
   * Gets whether the tile can be moved on
   * @return can it be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Gets the name of the image file of the Path
   * @return file name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
