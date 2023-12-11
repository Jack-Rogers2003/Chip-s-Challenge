/**
 * Class that represents the Trap Tile and handles its properties and
 * operations
 * @author Jack Jones
 * @author Dan Cross
 * @version 1.2
 */
public class Trap extends Tile {
  //Check whether the tile can be moved onto or not
  private static final boolean CAN_MOVE_ON = true;
  //The button connected to the trap
  private Button connectedButton = new Button();
  //Name of the file containing the image of the trap
  private static final String IMAGE_FILE = "Trap.png";

  /**
   * Constructor to create Trap object
   */
  public Trap() {
  }

  /**
   * Check if the trap is released by checking if the connected button has
   * been pressed
   * @return is the trap released
   */
  public boolean getIsReleased() {
    return connectedButton.getIsPressed();
  }

  /**
   * Sets the button the trap is connected to
   * @param newButton button to connect to the trap
   */
  public void setConnectedButton(Button newButton) {
    connectedButton = newButton;
  }

  /**
   * Check if the Trap can be moved onto
   * @return if can be moved onto
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Return name of the file with the Trap's image
   * @return file name
   */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
