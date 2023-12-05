public class ChipSocket extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private final int numberOfChips;
  private String imageFile;

  public ChipSocket(int newNumberOfChips) {
    numberOfChips = newNumberOfChips;
    switch (numberOfChips) {
      case 1 -> imageFile = "chip_socket_1.png";
      case 2 -> imageFile = "chip_socket_2.png";
      case 3 -> imageFile = "chip_socket_3.png";
    }
  }

  public int getNumberOfChips() {
    return numberOfChips;
  }

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getImageFile() {
    return imageFile;
  }
}
