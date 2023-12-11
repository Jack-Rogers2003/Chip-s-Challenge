import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class for the Level selector and anything relating to its window
 * Handles the buttons, pop-ups and any calls needed for the window to function
 * @author Jack Rogers
 * @version 1.3
 */
public class LevelSelector extends Application implements EventHandler<
        ActionEvent> {
    //Header of Level Selector window
    private static final Text WINDOW_HEADER = new Text("Choose a Level");
    //Back button for the window
    private static final Button BACK_BUTTON = new Button("Back");
    //Button for Level 1
    private static final Button LEVEL_1 = new Button("Level 1");
    //button for level 2
    private static final Button LEVEL_2 = new Button("Level 2");
    //button for level3
    private static final Button LEVEL_3 = new Button("Level 3");
    //button for level 4
    private static final Button LEVEL_4 = new Button("Level 4");
    //button for level 5
    private static final Button LEVEL_5 = new Button("Level 5");
    //Button for loading a game
    private static final Button LOAD_GAME_BUTTON = new Button("Load");
    //button for loading the leaderboard of level 1
    private static final Button LEVEL_1_LEADERBOARDS =
            new Button("Level 1 LeaderBoards");
    //button for loading the leaderboard of level 2
    private static final Button LEVEL_2_LEADERBOARDS =
            new Button("Level 2 LeaderBoards");
    //button for loading the leaderboard of level 3
    private static final Button LEVEL_3_LEADERBOARDS =
            new Button("Level 3 LeaderBoards");
    //button for loading the leaderboard of level 4
    private static final Button LEVEL_4_LEADERBOARDS =
            new Button("Level 4 LeaderBoards");
    //button for loading the leaderboard of level 5
    private static final Button LEVEL_5_LEADERBOARDS =
            new Button("Level 5 LeaderBoards");
    //Current working directory
    private static final String CURRENT_DIRECTORY =
            System.getProperty("user.dir");
    //Path to the directory storing leaderboards
    private static final String LEADERBOARDS_PATH = CURRENT_DIRECTORY +
            "\\Leaderboards\\";
    //Path to the directory storing Profiles
    private static final String PROFILES_PATH = CURRENT_DIRECTORY +
            "\\PlayerProfiles\\";
    //Path to the directory storing saved games
    private static final File SAVED_GAMES_PATH =
            new File(CURRENT_DIRECTORY + "\\saves\\");
    //Button to delete a profile
    private static final Button DELETE_PROFILE =
            new Button("Delete Profile");
    //Width of the window shown
    private static final int WINDOW_WIDTH = 500;
    //Height of the window shown
    private static final int WINDOW_HEIGHT = 300;
    //Maximum number of levels
    private static final int MAX_LEVEL_NUMBER = 5;
    //Style for the text
    private static final String TEXT_STYLE = "-fx-font: 12 arial";
    //Y translation for the window header
    private static final int WINDOW_HEADER_Y_TRANSLATION = -120;
    //X translation for the LeaderBoard and Delete buttons
    private static final int LEADERBOARD_AND_DELETE_X_TRANSLATION = 100;
    //Y translation for the Back, Load and Delete buttons
    private static final int BACK_LOAD_DELETE_Y_TRANSLATION = 100;
    //X translation for the Load button
    private static final int LOAD_X_TRANSLATION = -100;
    //Y translation for the Leaderboard and Level button for level 1
    private static final int LEADERBOARD_LEVEL_1_Y_TRANSLATION = -90;
    //Y translation for the Leaderboard and Level button for level 2
    private static final int LEADERBOARD_LEVEL_2_Y_TRANSLATION = -50;
    //Y translation for the Leaderboard and Level button for level 3
    private static final int LEADERBOARD_LEVEL_3_Y_TRANSLATION = -10;
    //Y translation for the Leaderboard and Level button for level 4
    private static final int LEADERBOARD_LEVEL_4_Y_TRANSLATION = 30;
    //Y translation for the Leaderboard and Level button for level 5
    private static final int LEADERBOARD_LEVEL_5_Y_TRANSLATION = 70;
    //Maximum amount of leaderboard places
    private static final int MAX_LEADERBOARD_PLACES = 10;
    //Width of the leaderboard popup
    private static final int LEADERBOARD_POPUP_WIDTH = 480;
    //height of the Leaderboard popup
    private static final int LEADERBOARD_POPUP_HEIGHT = 320;
    //The window being used
    private static Stage window;
    //The level the current profile has unlocked
    private static int unlockedLevels;

    /**
     * @param levelSelectWindow the primary stage for this application,
     *                          onto which the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage levelSelectWindow) {
    }

    /**
     * Checks if the level completed is the highest one available, and if
     * so updates the player profile to unlock the next level
     * @param levelCompleted level that has been completed
     */
    public static void updateUnlockedLevels(int levelCompleted) {
        if (levelCompleted != MAX_LEVEL_NUMBER) {
            if (levelCompleted == unlockedLevels) {
                unlockedLevels = unlockedLevels + 1;
                try {
                    FileWriter toUpdate = new FileWriter(PROFILES_PATH +
                            GameGUIManager.getCurrentProfile() + ".txt");
                    toUpdate.write(Integer.toString(unlockedLevels));
                    toUpdate.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            buttonEnable();
        }
    }

    /**
     * Generates a window and all of it's properties
     * @return A scene that is all Buttons and text needed for the window
     */
    public Scene generateMenu() {
        setButtonProperties();
        try {
            File toGetLevelsFrom = new File(PROFILES_PATH +
                    GameGUIManager.getCurrentProfile() + ".txt");
            Scanner fileReader = new Scanner(toGetLevelsFrom);
            unlockedLevels = Integer.parseInt(fileReader.nextLine());
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StackPane layout = new StackPane();
        buttonDisable();
        buttonEnable();
        layout.getChildren().add(WINDOW_HEADER);
        layout.getChildren().add(BACK_BUTTON);
        layout.getChildren().add(DELETE_PROFILE);
        layout.getChildren().add(LOAD_GAME_BUTTON);
        layout.getChildren().add(LEVEL_1);
        layout.getChildren().add(LEVEL_2);
        layout.getChildren().add(LEVEL_3);
        layout.getChildren().add(LEVEL_4);
        layout.getChildren().add(LEVEL_5);
        layout.getChildren().add(LEVEL_1_LEADERBOARDS);
        layout.getChildren().add(LEVEL_2_LEADERBOARDS);
        layout.getChildren().add(LEVEL_3_LEADERBOARDS);
        layout.getChildren().add(LEVEL_4_LEADERBOARDS);
        layout.getChildren().add(LEVEL_5_LEADERBOARDS);
        return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Sets the buttons used on the window
     */
    public void setButtonProperties() {
        WINDOW_HEADER.setStyle(TEXT_STYLE);
        WINDOW_HEADER.setTranslateY(WINDOW_HEADER_Y_TRANSLATION);
        BACK_BUTTON.setTranslateY(BACK_LOAD_DELETE_Y_TRANSLATION);
        BACK_BUTTON.setOnAction(this);
        LOAD_GAME_BUTTON.setTranslateY(BACK_LOAD_DELETE_Y_TRANSLATION);
        LOAD_GAME_BUTTON.setTranslateX(LOAD_X_TRANSLATION);
        LOAD_GAME_BUTTON.setOnAction(this);
        DELETE_PROFILE.setTranslateY(BACK_LOAD_DELETE_Y_TRANSLATION);
        DELETE_PROFILE.setTranslateX(LEADERBOARD_AND_DELETE_X_TRANSLATION);
        DELETE_PROFILE.setOnAction(this);
        LEVEL_1_LEADERBOARDS.setTranslateY(LEADERBOARD_LEVEL_1_Y_TRANSLATION);
        LEVEL_1_LEADERBOARDS.setTranslateX(
                LEADERBOARD_AND_DELETE_X_TRANSLATION);
        LEVEL_1_LEADERBOARDS.setOnAction(this);
        LEVEL_2_LEADERBOARDS.setTranslateY(LEADERBOARD_LEVEL_2_Y_TRANSLATION);
        LEVEL_2_LEADERBOARDS.setTranslateX(
                LEADERBOARD_AND_DELETE_X_TRANSLATION);
        LEVEL_2_LEADERBOARDS.setOnAction(this);
        LEVEL_3_LEADERBOARDS.setTranslateY(LEADERBOARD_LEVEL_3_Y_TRANSLATION);
        LEVEL_3_LEADERBOARDS.setTranslateX(
                LEADERBOARD_AND_DELETE_X_TRANSLATION);
        LEVEL_3_LEADERBOARDS.setOnAction(this);
        LEVEL_4_LEADERBOARDS.setTranslateY(LEADERBOARD_LEVEL_4_Y_TRANSLATION);
        LEVEL_4_LEADERBOARDS.setTranslateX(
                LEADERBOARD_AND_DELETE_X_TRANSLATION);
        LEVEL_4_LEADERBOARDS.setOnAction(this);
        LEVEL_5_LEADERBOARDS.setTranslateY(LEADERBOARD_LEVEL_5_Y_TRANSLATION);
        LEVEL_5_LEADERBOARDS.setTranslateX(
                LEADERBOARD_AND_DELETE_X_TRANSLATION);
        LEVEL_5_LEADERBOARDS.setOnAction(this);
        LEVEL_1.setTranslateY(LEADERBOARD_LEVEL_1_Y_TRANSLATION);
        LEVEL_1.setOnAction(this);
        LEVEL_2.setTranslateY(LEADERBOARD_LEVEL_2_Y_TRANSLATION);
        LEVEL_2.setOnAction(this);
        LEVEL_3.setTranslateY(LEADERBOARD_LEVEL_3_Y_TRANSLATION);
        LEVEL_3.setOnAction(this);
        LEVEL_4.setTranslateY(LEADERBOARD_LEVEL_4_Y_TRANSLATION);
        LEVEL_4.setOnAction(this);
        LEVEL_5.setTranslateY(LEADERBOARD_LEVEL_5_Y_TRANSLATION);
        LEVEL_5.setOnAction(this);
    }

    /**
     * Handles what happens when each button is pressed in the window
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        window = ((Stage) BACK_BUTTON.getScene().getWindow());
        if (event.getSource() == BACK_BUTTON) {
            GameGUIManager.setIsProfileSelectorWindowNext();
            window.close();
            GameGUIManager.windowChange();
        } else if (event.getSource() == DELETE_PROFILE) {
            if (checkDelete() == ButtonType.OK) {
                Game.deleteProfile(GameGUIManager.getCurrentProfile());
                GameGUIManager.setIsProfileSelectorWindowNext();
                window.close();
                GameGUIManager.windowChange();
            }
        } else if (event.getSource() == LOAD_GAME_BUTTON) {
            BoardGUI.setShouldLoad();
            GameGUIManager.setIsBoardWindowNext();
            window.close();
            GameGUIManager.windowChange();
        } else {
            leaderboardOrLevel(event);
        }
    }


    /**
     * Checks if the button pressed is a level button or a leaderboard button
     * @param event the button pressed
     */
    public void leaderboardOrLevel(ActionEvent event) {
        if (event.getSource() == LEVEL_1 || event.getSource() == LEVEL_2 ||
                event.getSource() == LEVEL_3 || event.getSource() == LEVEL_4 ||
                event.getSource() == LEVEL_5) {
            String levelToLoad = loadLevel(event.getSource());
            GameGUIManager.setCurrentLevel(levelToLoad);
            GameGUIManager.setIsBoardWindowNext();
            window.close();
            GameGUIManager.windowChange();
        } else if (event.getSource() == LEVEL_1_LEADERBOARDS ||
                event.getSource() == LEVEL_2_LEADERBOARDS ||
                event.getSource() == LEVEL_3_LEADERBOARDS ||
                event.getSource() == LEVEL_4_LEADERBOARDS ||
                event.getSource() == LEVEL_5_LEADERBOARDS) {
            String leaderboard = loadLeaderboard(event.getSource());
            leaderBoardDisplayBox(leaderboard);
        }
    }

    /**
     * Loads the leaderboard based on the button pressed
     * @param leaderboardPressed leaderboard button that has been pressed
     * @return level to load the leaderboard of
     */
    public String loadLeaderboard(Object leaderboardPressed) {
        String leaderboard;
        if (LEVEL_1_LEADERBOARDS.equals(leaderboardPressed)) {
            leaderboard = "level1";
        } else if (LEVEL_2_LEADERBOARDS.equals(leaderboardPressed)) {
            leaderboard = "level2";
        } else if (LEVEL_3_LEADERBOARDS.equals(leaderboardPressed)) {
            leaderboard = "level3";
        } else if (LEVEL_4_LEADERBOARDS.equals(leaderboardPressed)) {
            leaderboard = "level4";
        } else {
            leaderboard = "level5";
        }
        return leaderboard;
    }

    /**
     * Checks which level to load based on the button pressed
     * @param levelPressed level button pressed
     * @return name of level to load
     */
    public String loadLevel(Object levelPressed) {
        String level;
        if (LEVEL_1.equals(levelPressed)) {
            level = "level1";
        } else if (LEVEL_2.equals(levelPressed)) {
            level = "level2";
        } else if (LEVEL_3.equals(levelPressed)) {
            level = "level3";
        } else if (LEVEL_4.equals(levelPressed)) {
            level = "level4";
        } else {
            level = "level5";
        }
        return level;
    }

    /**
     * Checks through the unlocked levels, and enables level buttons
     * accordingly
     */
    public static void buttonEnable() {
        File save = new File(SAVED_GAMES_PATH + "\\" +
                GameGUIManager.getCurrentProfile() + ".txt");
        if (unlockedLevels >= 2) {
            LEVEL_2.setDisable(false);
        }
        if (unlockedLevels >= 3) {
            LEVEL_3.setDisable(false);
        }
        if (unlockedLevels >= 4) {
            LEVEL_4.setDisable(false);
        }
        if (unlockedLevels >= 5) {
            LEVEL_5.setDisable(false);
        }
        if (save.exists()) {
            LOAD_GAME_BUTTON.setDisable(false);
        }
    }

    /**
     * Checks though the levels unlocked, and disables level buttons
     * accordingly
     */
    public void buttonDisable() {
        File save = new File(SAVED_GAMES_PATH + "\\" +
                GameGUIManager.getCurrentProfile() + ".txt");
        if (unlockedLevels < 2) {
            LEVEL_2.setDisable(true);
        }
        if (unlockedLevels < 3) {
            LEVEL_3.setDisable(true);
        }
        if (unlockedLevels < 4) {
            LEVEL_4.setDisable(true);
        }
        if (unlockedLevels < 5) {
            LEVEL_5.setDisable(true);
        }
        if (!save.exists()) {
            LOAD_GAME_BUTTON.setDisable(true);
        }
    }

    /**
     * Pop up for deleting a player profile, checks if the user is sure they
     * want to delete a profile
     * @return button press
     */
    public ButtonType checkDelete() {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setTitle("Profile Deletion Confirm");
        alertBox.setHeaderText("Delete this profile?" +
                " You will not be able to recover it");
        alertBox.showAndWait();
        return alertBox.getResult();
    }

    /**
     * Gets the leaderboard of a level
     * @param level level of leaderboard wanted
     * @return array of Strings that contains the names and scores of the
     * leaderboard
     */
    public String[] getLeaderBoard(String level) {
        String[] leaderboardPlaces = new String[MAX_LEADERBOARD_PLACES];
        try {
            File leaderboard = new File(LEADERBOARDS_PATH + level +
                    ".txt");
            if(!leaderboard.exists()) {
                leaderboard.createNewFile();
            }
            Scanner fileReader = new Scanner(leaderboard);
            for (int i = 0; i < leaderboardPlaces.length; i++) {
                if (fileReader.hasNext()) {
                    leaderboardPlaces[i] = fileReader.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaderboardPlaces;
    }

    /**
     * Pop up to display the leaderboard
     * @param level level wanted leaderboard is wanted from
     */
    public void leaderBoardDisplayBox(String level) {
        Alert leaderboardPopup = new Alert(Alert.AlertType.INFORMATION);
        leaderboardPopup.getDialogPane().setPrefSize(LEADERBOARD_POPUP_WIDTH,
                LEADERBOARD_POPUP_HEIGHT);
        leaderboardPopup.setTitle("LeaderBoard");
        leaderboardPopup.setHeaderText("Here is the leaderboard for " + level);
        String[] leaderboardPlaces = getLeaderBoard(level);
        String textToDisplay = "User name:        Score: \n";
        for (String leaderboardPlace : leaderboardPlaces) {
            if (leaderboardPlace != null) {
                String[] line = leaderboardPlace.split(" ");
                String userName = line[0];
                String score = line[1];
                textToDisplay = textToDisplay + userName + "        " +
                        score + "\n";
            }
        }
        leaderboardPopup.setContentText(textToDisplay);
        leaderboardPopup.show();
    }
}