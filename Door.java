public class Door extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private final String colour;
  private String imageFile;
  

  public Door(String newColour) {
    colour = newColour;
    switch (colour) {
      case "R" -> imageFile = "RedDoor.png";
      case "Y" -> imageFile = "YellowDoor.png";
      case "G" -> imageFile = "GreenDoor.png";
      case "B" -> imageFile = "BlueDoor.png";
    }
  }

  public String getImageFile() {
    return imageFile;
  }

  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  public String getColour() {
    return colour;
  }
}
