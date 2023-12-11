import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
/**
 * Class that handles getting the string of a file
 * @author Annabel Ryan
 * @version 1.0
 */


public class Game {
    //Current directory working in
    private static final String CURRENT_DIRECTORY = System.getProperty(
            "user.dir");
    //Path to where levels are stored
    private static final File LEVELS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\Levels\\");
    //path to where Player profiles are stored
    private static final File PLAYER_PROFILES_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\PlayerProfiles\\");
    //path to where Leaderboard are stored
    private static final File LEADERBOARDS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\Leaderboards\\");
    //Path to where assets are stored
    private static final File ASSETS_PATH = new File(
            CURRENT_DIRECTORY + "\\assets\\");
    //path to where saved games are stored
    private static final File SAVED_GAMES_PATH = new File(
            CURRENT_DIRECTORY + "\\saves\\");

    private static final File STATS_PATH = new File(
            CURRENT_DIRECTORY + "\\stats\\");
    //Max number a player's username will have added to it
    private static final int MAX_PLAYER_NUMBER = 10000;
    //Length of ".txt" of a file
    private static final int FILE_EXTENSION_LENGTH = 4;

    /**
     * Constructor for game, checks if the directories necessary exist, and if
     * not, then it creates them
     */
    public Game() {
        if (!LEVELS_FILE_PATH.exists()) {
            LEVELS_FILE_PATH.mkdir();
        }
        if (!PLAYER_PROFILES_FILE_PATH.exists()) {
            PLAYER_PROFILES_FILE_PATH.mkdir();
        }
        if (!LEADERBOARDS_FILE_PATH.exists()) {
            LEADERBOARDS_FILE_PATH.mkdir();
        }
        if (!ASSETS_PATH.mkdir()) {
            ASSETS_PATH.mkdir();
        }
        if (!SAVED_GAMES_PATH.mkdir()) {
            SAVED_GAMES_PATH.mkdir();
        }
        if(!STATS_PATH.mkdir()) {
            STATS_PATH.mkdir();
        }
    }

    /**
     * Creates a new player Profile
     * @param userName name of the new profile
     * @return the final username of the profile
     * @throws IOException exception in case of file error
     */
    public String createNewPlayerProfile(String userName) throws IOException {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(MAX_PLAYER_NUMBER));
        File newPlayerProfile = new File(PLAYER_PROFILES_FILE_PATH,
                userName + "#" + id + ".txt");
        newPlayerProfile.createNewFile();
        FileWriter myWriter = new FileWriter(newPlayerProfile);
        myWriter.write("1");
        myWriter.close();
        return userName + "#" + id;
    }

    /**
     * Returns the file path of the level given
     * @param level level searching the file path for
     * @return File path of the level;
     */
    public File getLevel(String level) {
        return new File(LEVELS_FILE_PATH + "\\" + level + ".txt");
    }

    /**
     * Returns an ArrayList of all created player profiles
     * @return list of all player profiles
     */
    public ArrayList<String> getAllPlayerNames() {
        ArrayList<String> userNames = new ArrayList<>();
        File[] files = new File(String.valueOf(PLAYER_PROFILES_FILE_PATH)).
                listFiles();
        assert files != null;
        for (File fileName : files) {
            String currentName = fileName.getName();
            userNames.add(currentName.substring(0, currentName.length() -
                    FILE_EXTENSION_LENGTH));
        }
        return userNames;
    }

    /**
     * Deletes the player profile with the username given
     * @param userName profile to be deleted
     */
    public static void deleteProfile(String userName) {
        File toDelete = new File(PLAYER_PROFILES_FILE_PATH + "\\" +
                userName + ".txt");
        toDelete.delete();
    }
}
