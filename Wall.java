public class Wall extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private static final String IMAGE_FILE = "Wall.png";

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return IMAGE_FILE;
  }
}
