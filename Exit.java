public class Exit extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Exit.png";

      /**
     * Gets the boolean value of whether or not the exit can be moved on.
     *
     * @return The boolean value of whether or not the exit can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

    /**
    * Gets the image file of the exit.
    *
    * @return The String of the image file of the exit.
    */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
