/**
 * Class that handles the dirt Tile and its associated properties
 * @author Dan Cross
 * @author Jack Jones
 * @version 1.0
 */
public class Dirt extends Tile {
  //Boolean for whether the tile can be moved on by a player
  private static final boolean CAN_MOVE_ON = true;
  //File name of the image of Dirt
  private static final String IMAGE_FILE = "dirt.png";

  /**
   * Returns whether the tile can be moved on by the player
   * @return if the tile can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the file name of the image of the Dirt
   * @return File name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }

}
