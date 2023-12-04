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

  public void releaseTrap() {
    isReleased = true;
  }

  public boolean getIsReleased() {
    return isReleased;
  }

  public boolean getIsConnected() {
    return isConnected;
  }

  public void setConnectedButton(Button newButton) {
    connectedButton = newButton;
    isConnected = true;
  }

  public Button getConnectedButton() {
    return connectedButton;
  }

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return IMAGE_FILE;
  }
}
