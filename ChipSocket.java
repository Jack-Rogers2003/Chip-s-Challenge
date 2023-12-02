public class ChipSocket extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private final int numberOfChips;
  private String imageFile;

  public ChipSocket(int newNumberOfChips) {
    numberOfChips = newNumberOfChips;
    switch (numberOfChips) {
      case 1 -> imageFile = "ChipSocket1.png";
      case 2 -> imageFile = "ChipSocket2.png";
      case 3 -> imageFile = "ChipSocket3.png";
    }
  }


          /**
     * Gets the int value of the number of chips.
     *
     * @return The int value of the number of chips.
     */
  public int getNumberOfChips() {
    return numberOfChips;
  }

      /**
     * Gets the boolean value of whether or not the chipSocket can be moved on.
     *
     * @return The boolean value of whether or not the chipSocket can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

    /**
    * Gets the image file of the chipSocket.
    *
    * @return The String of the image file of the chipSocket.
    */
  public String getImageFile() {
    return imageFile;
  }
}
