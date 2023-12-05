import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
/**
 * Class that handles getting the string of a file
 * @author Annabel Ryan
 * @version 1.0
 */


public class Game {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final File LEVELS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\src\\main\\java\\Levels\\");

    private static final File PLAYER_PROFILES_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\src\\main\\java\\PlayerProfiles\\");
    private static final File LEADERBOARDS_FILE_PATH
            = new File(CURRENT_DIRECTORY + "\\src\\main\\java\\Leaderboards\\");

    public Game() {
        if(!LEVELS_FILE_PATH.exists()) {
            LEVELS_FILE_PATH.mkdir();
        }
        if(!PLAYER_PROFILES_FILE_PATH.exists()) {
            PLAYER_PROFILES_FILE_PATH.mkdir();
        }
        if (!LEADERBOARDS_FILE_PATH.exists()) {
            LEADERBOARDS_FILE_PATH.mkdir();
        }
    }

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

    public ArrayList<String> getLeaderboard(String level) throws FileNotFoundException {
        File leaderboardFile = new File (LEADERBOARDS_FILE_PATH + level + ".txt");
        Scanner fileReader = new Scanner(leaderboardFile);
        ArrayList<String> leaderBoard = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            leaderBoard.add(fileReader.nextLine());
        }
        return leaderBoard;
    }

    public File getLevel(String level) {
        return new File(LEVELS_FILE_PATH + "\\" + level + ".txt");
    }

    public File getLevelLeaderboard(String level) {
        return new File(LEADERBOARDS_FILE_PATH + "\\" + level + ".txt");
    }

    public File getNextLevel(String level) {
        int currentLevelNumber = level.charAt(6) - '0';
        String nextLevel = "Level" + (currentLevelNumber + 1) + ".txt";
        return getLevel(nextLevel);
    }

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

    public static void deleteProfile(String userName) {
        File toDelete = new File(PLAYER_PROFILES_FILE_PATH + "\\" + userName + ".txt");
        toDelete.delete();
    }

    public static int getCompletedLevels(String userName) {
        try {
            File toGetLevelsFrom = new File(PLAYER_PROFILES_FILE_PATH + "\\" + userName + ".txt");
            Scanner fileReader = new Scanner(toGetLevelsFrom);
            String levelsCompleted = fileReader.nextLine();
            fileReader.close();
            return Integer.parseInt(levelsCompleted);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}