/**
 * This class represents the door tile and the conditions that it creates
 * @author Benji Brew, Jack Rogers
 * @version 1.1
 */

public class Door extends Tile {
  private static final boolean CAN_MOVE_ON = false;
  private final String colour;
  private String imageFile;

  /**
   * Contructer for the door tile that specifies which colour door to use
   *
   * @param newColour the colour of the door
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
   * Method that retrieves the image file of the door
   *
   * @return the image file
   */
  public String getImageFile() {
    return imageFile;
  }

  /**
   * Method that checks if an actor can move onto a door
   *
   * @return the boolean value of CAN_MOVE_ON
   */
  public boolean getCanMoveOn() {
    return CAN_MOVE_ON;
  }

  /**
   * Method that gets the colour of the door
   *
   * @return the colour of the door
   */
  public String getColour() {
    return colour;
  }
}
