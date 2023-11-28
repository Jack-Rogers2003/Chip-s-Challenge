import java.io.File;

/**
 * Class that handles getting the string of a file
 * @author
 * @version 1.0
 */


public class Game {

    /**
     * Method that creates a board object
     * @param nextLevel String input that is the name of the file
     */
    public static void getNextLevel(String nextLevel) {

        String levelPath = "C:\\Users\\annab\\OneDrive\\Documents\\Code\\CS-230-A2-GUI-Branch\\levels"
                + nextLevel; //sorry ik the directory is wrong pls fix it

        try {
            File level = new File(levelPath);
            String lvlName = level.getName();
            System.out.println("File Name: " + lvlName);

            new Board(lvlName);

        } catch (Exception e) {
            System.out.println("Error"); //prints error after but idk why?
        }
    }

    /**
     * Method that reads all the files in a folder
     */
    public static void listFiles() {
        try {
            File directory = new File("C:\\Users\\annab\\OneDrive\\Documents\\Code\\CS-230-A2-GUI-Branch\\" +
                    "levels");
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Method that splits the string of a file, where it will get level
     * number and turn it into and int
     * @param level String input that is the name of the file
     */
    public static void splitString(String level) {
        String[] parts = level.split("[_.]");
        String levelString = parts[1];
        int levelNumber = Integer.parseInt(levelString);
        System.out.println(levelNumber);
    }

    public static void main(String[] args) {
        getNextLevel("level_1.txt");
        listFiles();
        splitString("level_1.txt");
        //just testing if things work
    }
}