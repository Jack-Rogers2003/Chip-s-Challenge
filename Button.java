public class Button extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private boolean isPressed = false;
  private static final String IMAGE_FILE = "Button.png";

  public boolean getIsPressed() {
    return isPressed;
  }

  public void setIsPressed() {
    isPressed = true;
  }

      /**
     * Gets the boolean value of whether or not the button can be moved on.
     *
     * @return The boolean value of whether or not the button can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

    /**
    * Gets the image file of the button.
    *
    * @return The String of the image file of the button.
    */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
