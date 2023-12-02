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

      /**
    * Gets the image file of the door.
    *
    * @return The String of the image file of the door.
    */
  public String getImageFile() {
    return imageFile;
  }

        /**
     * Gets the boolean value of whether or not the door can be moved on.
     *
     * @return The boolean value of whether or not the door can be moved on.
     */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

      /**
    * Gets the colour of the door.
    *
    * @return The String of the colour of the door.
    */
  public String getColour() {
    return colour;
  }
}
