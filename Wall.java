public class Wall extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private static final String IMAGE_FILE = "Wall.png";

    /**
     * Gets the boolean value of whether or not the wall can be moved on.
     *
     * @return The boolean value of whether or not the wall can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
    * Gets the image file of the wall.
    *
    * @return The String of the image file of the wall.
    */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
