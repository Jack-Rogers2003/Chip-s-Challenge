public class Ice extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private final String imageFile;
  private final String corner;

  public Ice(String newCorner) {
    corner = newCorner;
    switch (corner) {
      case "BR" -> imageFile = "BottomRightIce";
      case "BL" -> imageFile = "BottomLeftIce";
      case "TR" -> imageFile = "TopRightIce";
      case "TL" -> imageFile = "TopLeftIce";
      default -> imageFile = "Ice";
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
