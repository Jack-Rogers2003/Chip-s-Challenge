public class Key extends Item {
    private final String colour;
    private String imageFile = "";

    /**
    * Class that allocates a colour to a key
    * @author Jack Rogers
    * @version 1.1
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

    public String getImageFile() {
        return imageFile;
    }

    public String getColour() {
        return colour;
    }
}
