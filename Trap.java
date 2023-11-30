public class Trap extends Tile {
  private boolean isConnected = False;
  private boolean moveability = True;
  private boolean isReleased = False;
  private Button connectedButton = new Button;


  //The purpose of having two constructors is in case a trap is created but the button for it
  //Hasn't yet, this lets the trap stil be created without crashing the program
  public Trap(Button newButton) {
    connctedButton = newButton;
    isConncted = True;
  }

  public Trap() {
  }

  public void releaseTrap() {
    isReleased = True;
  }

  public boolean getIsReleased() {
    return isReleased;
  }

  public void setConnectedButton(Button newButton) {
    connectedButton = newButton;
  }

  public Button getConnctedButton() {
    return connectedButton;
  }

  public boolean getMoveability() {
    return moveability;
  }
}
