public class Door extends Tile {

  private String colour;
  private boolean moveability = False;
  private String imageFile;
  

  public Tile(String newColour) {
    colour = newColour;
    switch (colour) {
      case "R":
        imageFile = "file:redDoor.png";
        break;
      case "Y":
        imageFile = "file:yellowDoor.png";
        break;
      case "G":
        imageFile = "file:greenDoor.png";
        break;
      case "B":
        imageFile = "file:blueDoor.png";
        break;
    }
  }

  public String getFilePath() {
    return imageFile;
  }

  public boolean getMoveability() {
    return moveability;
  }
}
