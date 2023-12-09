import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Class that handles where the program is initially ran, plus the GUI and swapping between windows
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */
public class GameGUIManager extends Application {
    public static boolean isProfileSelectorWindowNext = false;
    public static boolean isLevelSelectorWindowNext = false;
    public static boolean isBoardWindowNext = false;
    private static final ProfileSelectorWindow PROFILE_SELECTOR_WINDOW = new ProfileSelectorWindow();
    private static final Stage WINDOW = new Stage();
    private static String currentProfile = "";
    private static String currentLevel = "";

    /**
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        openProfileWindow();
    }

    /**
     * Sets what the currently used profile is
     * @param newProfile A string that is the username of the current
     * profile being used
     */
    public static void setCurrentProfile(String newProfile) {
        currentProfile = newProfile;
    }

    /**
     * Returns what the currently selected profile is
     * @return A String that is the userName of the current profile being used
     */
    public static String getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Returns what the currently selected level is
     * @return A String that is the value of the current level being played
     */
    public static String getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets what level is being played
     * @param newLevel A string that will be the current level of the
     * game being played
     */
    public static void setCurrentLevel(String newLevel) {
        currentLevel = newLevel;
    }

    /**
     * Handles opening of the profile selector window
     */
    private static void openProfileWindow() {
        Scene profileWindow = PROFILE_SELECTOR_WINDOW.generateMenu();
        WINDOW.setTitle("Select or Create Profile");
        WINDOW.setScene(profileWindow);
        WINDOW.show();
    }

    /**
     * Handles opening of the Level Selector window
     */
    private static void openLevelSelectorWindow() {
        LevelSelector levelSelector = new LevelSelector();
        Scene levelSelectWindow = levelSelector.generateMenu();
        WINDOW.setTitle("Welcome " + getCurrentProfile());
        WINDOW.setScene(levelSelectWindow);
        WINDOW.show();
    }

    /**
     * Handles opening of the board window
     */
    private static void openBoardWindow() {
        BoardGUI newBoard = new BoardGUI();
        Scene boardWindow = newBoard.generateBoard();
        WINDOW.setTitle(getCurrentLevel());
        WINDOW.setScene(boardWindow);
        WINDOW.show();
    }

    /**
     * Checks what the next window is to open
     */
    public static void windowChange() {
        if (isLevelSelectorWindowNext) {
            openLevelSelectorWindow();
            isLevelSelectorWindowNext = false;
        } else if (isProfileSelectorWindowNext) {
            openProfileWindow();
            isProfileSelectorWindowNext = false;
        } else if (isBoardWindowNext) {
            openBoardWindow();
            isBoardWindowNext = false;
        }
    }
}
