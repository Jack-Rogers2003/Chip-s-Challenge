/**
 * This class creates the Button tile
 * Specifies whether an actor is allowed to interact with a button
 * @author Jack Jones, Benji Brew
 * @version 1.1
 */

public class Button extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private boolean isPressed = false;
  private static final String IMAGE_FILE = "Button.png";

        /**
     * Gets the boolean value of whether or not the button is pressed.
     *
     * @return The boolean value of whether or not the button is pressed.
     */
  public boolean getIsPressed() {
    return isPressed;
  }

      /**
     * Sets the boolean value of whether or not the button is pressed.
     */
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
