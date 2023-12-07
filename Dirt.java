/**
  * This class creates the Dirt tile
  * Specifies the conditions the dirt tile creates for an actor
  * @author Jack Jones, Benji Brew
  * @version 1.1
  */

public class Dirt extends Tile {
  
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Dirt.png";

  /**
    * Gets the boolean value of whether or not the dirt can be moved on.
    *
    * @return The boolean value of whether or not the dirt can be moved on.
    */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
    * Gets the image file of the dirt.
    *
    * @return The String of the image file of the dirt.
    */
  public String getImageFile() {
    return IMAGE_FILE;
  }

}
