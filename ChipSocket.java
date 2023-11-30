public class ChipSocket extends Tile {
  private boolean moveability = False;
  private int numberOfChips;
  private String imageFile;

  public ChipSocket(int newNumberOfChips) {
    numberofChips = newNumberOfChips;
    switch(numberOfChips) {
      case 1:
        imageFile = "file:chipSocket1.png";
        break;
      case 2:
        imageFile = "file:chipSocket2.png";
        break;
      case 3:
        imageFile = "file:chipSocket3.png";
        break;
    }
  }

  public int getNumberOfChips() {
    return numberOfChips;
  }

  public boolean getMoveability() {
    return moveability;
  }

  public String getFilePath() {
    return imageFile;
  }
}
