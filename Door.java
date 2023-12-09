public class Door extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private final String colour;
  private String imageFile;

  public Door(String newColour) {
    colour = newColour;
    switch (colour) {
      case "R" -> imageFile = "red_door.png";
      case "Y" -> imageFile = "yellow_door.png";
      case "G" -> imageFile = "green_door.png";
      case "B" -> imageFile = "blue_door.png";
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
