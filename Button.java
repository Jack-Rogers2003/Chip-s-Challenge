/**
 * Class that represents the button tile and any related properties and
 * operations needed
 * @author Dan Cross
 * @author Jack Jones
 * @version 1.0
 */
public class Button extends Tile {
  //
  private static final boolean CAN_MOVE_ON = true;
  //Boolean whether the button has been pressed or not
  private boolean isPressed = false;
  //Name of the file containing the Button's image
  private static final String IMAGE_FILE = "Button.png";

  /**
   * Returns whether the button has been pressed on or not
   * @return boolean whether isPressed is true or false
   */
  public boolean getIsPressed() {
    return isPressed;
  }

  /**
   * Sets that the button has been pressed on
   */
  public void setIsPressed() {
    isPressed = true;
  }

  /**
   * Returns a boolean whether the tile is movable on by the Player
   * @return boolean whether the player can move on it
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the name of the file containing the image of the button
   * @return String that is the file name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
