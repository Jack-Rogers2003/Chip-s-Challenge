/**
 * This class creates the Trap tile
 */

public class Trap extends Tile {
  private boolean isConnected = false;
  private static final boolean CAN_MOVE_ON = true;
  private boolean isReleased = false;
  private Button connectedButton = new Button();
  private static final String IMAGE_FILE = "Trap.png";


  //The purpose of having two constructors is in case a trap is created but the button for it
  //Hasn't yet, this lets the trap still be created without crashing the program
  public Trap(Button newButton) {
    connectedButton = newButton;
    isConnected = true;
  }

  public Trap() {
  }

      /**
     * Sets the trap as released.
     *
     */
  public void releaseTrap() {
    isReleased = true;
  }

        /**
     * Gets the boolean value of whether or not the trap is released.
     *
     * @return The boolean value of whether or not the trap is released.
     */
  public boolean getIsReleased() {
    return isReleased;
  }

        /**
     * Gets the boolean value of whether or not the trap is connected.
     *
     * @return The boolean value of whether or not the trap is connected.
     */
  public boolean getIsConnected() {
    return isConnected;
  }

        /**
     * Sets the connected button.
     *
     * @param the new button to be set.
     */
  public void setConnectedButton(Button newButton) {
    connectedButton = newButton;
    isConnected = true;
  }


        /**
     * Gets the Button value of the connected button.
     *
     * @return The the Button value of the connected button.
     */
  public Button getConnectedButton() {
    return connectedButton;
  }

        /**
     * Gets the boolean value of whether or not the trap can be moved on.
     *
     * @return The boolean value of whether or not the trap can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

      /**
    * Gets the image file of the trap.
    *
    * @return The String of the image file of the trap.
    */
  public String getImageFile() {
    return IMAGE_FILE;
  }
}
