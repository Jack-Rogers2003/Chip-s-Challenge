/**
 * Class that handles the Wall tile and its associated properties and
 * operations
 * @author Jack Jones
 * @author Dan Cross
 */
public class Wall extends Tile {
  //Whether the tile can be moved on or not
  private static final boolean CAN_MOVE_ON = false;
  //The name of the file containing the image of the Wall tile
  private static final String IMAGE_FILE = "wall.png";

  /**
   * Check whether the Wall tile can be moved onto or not
   * @return if it can be moved onto
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the name of the file containing
   * @return file name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
