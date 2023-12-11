/**
 * Class that handles the ChipSocket tile, and it's related properties and
 * operations
 * @author Dan Cross
 * @author Jack Jons
 */
public class ChipSocket extends Tile {
  //Whether the tile can be moved on by the player or not
  private static final boolean CAN_MOVE_ON = false;
  //The number of chips needed for the chipSocket to be opened
  private final int numberOfChips;
  //The name of the file containing the image for the chipSocket
  private String imageFile;

  /**
   * The constructor for the chipSocket, sets the image file depending on
   * how many chips is needed to open it
   * @param newNumberOfChips The number of chips needed to open it
   */
  public ChipSocket(int newNumberOfChips) {
    numberOfChips = newNumberOfChips;
    switch (numberOfChips) {
      case 1 -> imageFile = "chip_socket_1.png";
      case 2 -> imageFile = "chip_socket_2.png";
      case 3 -> imageFile = "chip_socket_3.png";
    }
  }

  /**
   * Returns the number of chips needed to open the socket
   * @return chip amount
   */
  public int getNumberOfChips() {
    return numberOfChips;
  }

  /**
   * Returns whether the chipSocket can be moved on or not
   * @return boolean if it can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the file name for the ChipSocket
   * @return String that is the file name of the chipSocket
   */
  public String getImageFile() {
    return imageFile;
  }
}
