public class Dirt extends Tile {
  private static final boolean CAN_MOVE_ON = true;
  private static final String IMAGE_FILE = "Dirt.png";

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return IMAGE_FILE;
  }

}
