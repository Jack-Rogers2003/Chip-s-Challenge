import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
/**
 * Class that handles getting the string of a file and initiates the game
 * @author Annabel Ryan, Benji Brew
 * @version 1.1
 */


public class Game {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final File LEVELS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\Levels\\");

    private static final File PLAYER_PROFILES_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\PlayerProfiles\\");
    private static final File LEADERBOARDS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\Leaderboards\\");
    private static final File ASSETS_PATH = new File(CURRENT_DIRECTORY +
            "\\assets\\");

    /**
     * Contructor for the game that checks all of the neccasary files are present
     */
    public Game() {
        if(!LEVELS_FILE_PATH.exists()) {
            LEVELS_FILE_PATH.mkdir();
        }
        if(!PLAYER_PROFILES_FILE_PATH.exists()) {
            PLAYER_PROFILES_FILE_PATH.mkdir();
        }
        if (!LEADERBOARDS_FILE_PATH.exists()) {
            LEADERBOARDS_FILE_PATH.mkdir();
        } if (ASSETS_PATH.mkdir()) {
            ASSETS_PATH.mkdir();
        }
    }

    /**
     * Method that creates a player profile and writes it to a file
     *
     * @param userName the name of the player to be used
     * @return the username and the # id of the player
     */
    public String createNewPlayerProfile(String userName) throws IOException {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        File newPlayerProfile = new File(PLAYER_PROFILES_FILE_PATH,
                userName + "#" + id + ".txt");
        newPlayerProfile.createNewFile();
        FileWriter myWriter = new FileWriter(newPlayerProfile);
        myWriter.write("1");
        myWriter.close();
        return userName + "#" + id;
    }

    /**
     * Method that retrieves the leaderboard for the current level of the game
     *
     * @param level the current level the game is running on
     * @return the leaderboard
     */
    public ArrayList<String> getLeaderboard(String level) throws FileNotFoundException {
        File leaderboardFile = new File (LEADERBOARDS_FILE_PATH + level + ".txt");
        Scanner fileReader = new Scanner(leaderboardFile);
        ArrayList<String> leaderBoard = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            leaderBoard.add(fileReader.nextLine());
        }
        return leaderBoard;
    }

    /**
     * Method that gets the current level the game is running on
     *
     * @return the file path of the level that the game is running on
     */
    public File getLevel(String level) {
        return new File(LEVELS_FILE_PATH + "\\" + level + ".txt");
    }

    /**
     * Method that gets the leaderboard for the level the game is running on
     *
     * @param level the level the game is running on
     * @return the file path for the leaderboard of the current level
     */
    public File getLevelLeaderboard(String level) {
        return new File(LEADERBOARDS_FILE_PATH + "\\" + level + ".txt");
    }

    /**
     * Method that retrieves the next level of the game
     *
     * @param level the current level the game is running on
     * @return the next level
     */
    public File getNextLevel(String level) {
        int currentLevelNumber = level.charAt(6) - '0';
        String nextLevel = "Level" + (currentLevelNumber + 1) + ".txt";
        return getLevel(nextLevel);
    }

    /**
     * Method that retrives the names of all players
     *
     * @return the list of all players names
     */
    public ArrayList<String> getAllPlayerNames() {
        ArrayList<String> userNames = new ArrayList<>();
        File[] files = new File(String.valueOf(PLAYER_PROFILES_FILE_PATH)).listFiles();
        assert files != null;
        for (File fileName : files) {
            String currentName = fileName.getName();
            userNames.add(currentName.substring(0, currentName.length()-4));
        }
        return userNames;
    }

    /**
     * Method that deletes the profile of a player
     *
     * @param userNmae the name of the players profile to be deleted
     */
    public static void deleteProfile(String userName) {
        File toDelete = new File(PLAYER_PROFILES_FILE_PATH + "\\" + userName + ".txt");
        toDelete.delete();
    }

}
