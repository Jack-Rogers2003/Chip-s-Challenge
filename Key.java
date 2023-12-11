/**
 * class that handles the key item and it's properties and operations
 */
public class Key extends Item {
    //Colour of the key
    private final String colour;
    //Name of the file with the image of the key
    private String imageFile = "";

    /**
     * Constructor for key, set's colour and then sets imageFile
     * correspondingly
     * @param newColour Colour of the key
     */
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
     * Returns name of the file with the image of the key
     * @return file name
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * Returns the colour of the key
     * @return key colour
     */
    public String getColour() {
        return colour;
    }
}
