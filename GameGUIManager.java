import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Where the program is initially ran, handles the GUI and swapping
 * between windows
 * @author Jack Rogers
 * @version 1.3
 */
public class GameGUIManager extends Application {
    //Flag if profile Selection window is the next to be opened
    private static boolean isProfileSelectorWindowNext = false;
    ////Flag if the level selector window is the next to be opened
    private static boolean isLevelSelectorWindowNext = false;
    //Flag if the board window is the next to be opened
    private static boolean isBoardWindowNext = false;
    //Instance of the profile selection window
    private static final ProfileSelectorWindow PROFILE_SELECTOR_WINDOW =
            new ProfileSelectorWindow();
    //Window that will be used to display in
    private static final Stage WINDOW = new Stage();
    //Current selected profile
    private static String currentProfile = "";
    //Current selected level
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
     *                   profile being used
     */
    public static void setCurrentProfile(String newProfile) {
        currentProfile = newProfile;
    }

    public static void setIsProfileSelectorWindowNext() {
        isProfileSelectorWindowNext = !isProfileSelectorWindowNext;
    }


    public static void setIsLevelSelectorWindowNext() {
        isLevelSelectorWindowNext = !isLevelSelectorWindowNext;
    }

    public static void setIsBoardWindowNext() {
        isBoardWindowNext = !isBoardWindowNext;
    }

    /**
     * Returns what the currently selected profile is
     * @return A String that is the userName of the current profile being used
     */
    public static String getCurrentProfile() {
        return currentProfile;
    }

    public static String getCurrentLevel() {
        return currentLevel;
    }

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
