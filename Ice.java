/**
 * Class that handles the ice tile containing boolean methods that are used
 * to determine the environment created for an actor when interacted with
 * @author Jack Jones, Benji Brew
 * @version 1.1
  */

public class Ice extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private String imageFile = "";
  private final String corner;

  /**
   * Constructor that is used to create instances of an ice tile
   *
   * @param newCorner A string that is used to decide which type of ice tile
   * is used
   */
  public Ice(String newCorner) {
    corner = newCorner;
    switch (corner) {
      case "B" -> imageFile = "BottomRightIce.png";
      case "L" -> imageFile = "BottomLeftIce.png";
      case "R" -> imageFile = "TopRightIce.png";
      case "T" -> imageFile = "TopLeftIce.png";
      case "N" -> imageFile = "Ice.png";
    }
  }

  /**
   * Method that gets the specific corner being used
   *
   * @return the corner being used
   */
  public String getCorner() {
    return corner;
  }

  /**
   * Method that decides whether an ice tile can be moved on
   *
   * @return the boolean value of CAN_MOVE_ON
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

 /**
  * Method that retrieves the image file containing the correct ice tile
  *
  * @return the image file
  */
  public String getImageFile() {
    return imageFile;
  }
}
