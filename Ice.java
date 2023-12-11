/**
 * Class that handles the Ice tile and its related properties and operations
 * @author Jack Jones
 * @author Dan Cross
 * @version 1.1
 */
public class Ice extends Tile {
  //Whether the ice tile can be moved on by the player
  private static final boolean CAN_MOVE_ON = true;
  //The name of the file containing the image of the ice tile
  private String imageFile = "";
  //Corner for the ice tile
  private final String corner;

  /**
   * Constructor for Ice, sets what the corner is and then sets the imageFile
   * corresponding to it
   * @param newCorner corner of the tile
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
   * Returns the corner of the ice tile
   * @return ice tile corner
   */
  public String getCorner() {
    return corner;
  }

  /**
   * Returns whether the ice tile can be moved on or not
   * @return is the player can move on it
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the name of the file that contains the image of the ice tile
   * @return file name
   */
  public String getImageFile() {
    return imageFile;
  }
}
