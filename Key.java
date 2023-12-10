/**
 * Class that allocates a colour to a key
 * @author Jack Rogers
 * @version 1.1
 */

public class Key extends Item {
    private final String colour;
    private String imageFile = "";

    /**
    * Constructor for the key class
    *@param newColour String input that is the initial of the colour we want to allocate
    * to the key.
    **/

    public Key(String newColour) {
        colour = newColour;
        switch (colour) {
            case "R" -> imageFile = "red_key.png";
            case "Y" -> imageFile = "yellow_key.png";
            case "B" -> imageFile = "blue_key.png";
            case "G" -> imageFile = "green_key.png";
        }
    }

    /**
    * Method that returns the imageFile value
    *
    * @return the image file
    **/

    public String getImageFile() {
        return imageFile;
    }

    /**
    * Method that returns the colour value
    *
    * @return the colour value
    **/

    public String getColour() {
        return colour;
    }
}
