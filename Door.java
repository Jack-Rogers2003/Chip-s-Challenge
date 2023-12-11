/**
 * Class that handles the Door and its associated properties and operations
 */
public class Door extends Tile {
  //Whether the tile can be moved on or not
  private static final boolean CAN_MOVE_ON = false;
  //Colour of the door
  private final String colour;
  //File containing image of the door
  private String imageFile;

  /**
   * Consturctor for the door that sets its colour, and from it sets the
   * file name for its image
   * @param newColour colour of the door
   */
  public Door(String newColour) {
    colour = newColour;
    switch (colour) {
      case "R" -> imageFile = "red_door.png";
      case "Y" -> imageFile = "yellow_door.png";
      case "G" -> imageFile = "green_door.png";
      case "B" -> imageFile = "blue_door.png";
    }
  }

  /**
   * Returns name of the file that contains the door's image
   * @return file name of the image
   */
  public String getImageFile() {
    return imageFile;
  }

  /**
   * Returns whether the door can be moved on or not
   * @return boolean if the door can be moved on
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Returns the colour of the door as a String
   * @return door colour
   */
  public String getColour() {
    return colour;
  }
}
