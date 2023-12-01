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

      /**
     * Gets the boolean value of whether or not the ice can be moved on.
     *
     * @return The boolean value of whether or not the ice can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

    /**
    * Gets the image file of the ice.
    *
    * @return The String of the image file of the ice.
    */
  public String getImageFile() {
    return imageFile;
  }
}
