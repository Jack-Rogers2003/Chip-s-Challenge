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

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return IMAGE_FILE;
  }
}
