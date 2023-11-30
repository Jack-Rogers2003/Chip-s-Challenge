public class Button extends Tile {
  private boolean moveability = True;
  private boolean isPressed = False;

  public boolean getIsPressed() {
    return isPressed;
  }

  public void setIsPressed() {
    isPressed = True;
  }

  public boolean getMoveability() {
    return moveability;
  }
}
