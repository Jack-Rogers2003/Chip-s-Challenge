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

  public String getCorner() {
    return corner;
  }

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return imageFile;
  }
}
