/**
 * This class represents the button tile and handles its behaviours
 * when interacted with
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */

public class Button extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private boolean isPressed = false;
  private static final String IMAGE_FILE = "Button.png";

  /**
   * Method that checks whether a button has been pressed
   *
   * @return the boolean value of isPressed
   */
  public boolean getIsPressed() {
    return isPressed;
  }

  /**
   * Method that sets whether a button has been pressed
   */
  public void setIsPressed() {
    isPressed = true;
  }

  /**
   * Method that checks whether an actor can move onto a button
   *
   * @return the boolean value of whether the button can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Method that retrieves the png image of a button
   *
   * @return the image file
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
