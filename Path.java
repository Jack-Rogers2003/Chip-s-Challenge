public class Path extends Tile {

  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Path.png";

      /**
     * Gets the boolean value of whether or not the path can be moved on.
     *
     * @return The boolean value of whether or not the path can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

    /**
     * Gets the image file of the path.
     *
     * @return The String of the image file of the path.
     */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
