/**
 * Class that represents the dirt tile and the conditions that it creates
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */

public class Dirt extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "dirt.png";

  /**
   * Method that checks if a dirt tile can be moved on
   * 
   * @return the boolean value of whether the tile can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Method that retrieves the image file of the dirt tile
   *
   * @return the image file
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }

}
