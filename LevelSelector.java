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
 * @author Jack Rogers, Benji Brew
 * @version 1.1
 */
public class LevelSelector extends Application implements EventHandler<ActionEvent> {

    private static final Text WINDOW_HEADER = new Text("Choose a Level");
    private static final Button BACK_BUTTON = new Button("Back");
    private static final Button LEVEL_1 = new Button("Level 1");
    private static final Button LEVEL_2 = new Button("Level 2");
    private static final Button LEVEL_3 = new Button("Level 3");
    private static final Button LEVEL_4 = new Button("Level 4");
    private static final Button LEVEL_5 = new Button("Level 5");
    private static final Button LEVEL_1_LEADERBOARDS =
            new Button("Level 1 LeaderBoards");
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String LEADERBOARDS_PATH = CURRENT_DIRECTORY +
            "\\Leaderboards\\";
    private static final String PROFILES_PATH = CURRENT_DIRECTORY +
            "\\PlayerProfiles\\";
    private static final Button DELETE_PROFILE = new Button("Delete Profile");
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static Stage window;
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
     * Method that checks what levels a player can access
     *
     * @param levelCompleted ant int of the number of levels a player
     * has completed
    */

    public static void updateUnlockedLevels(int levelCompleted) {
        if(levelCompleted != 5) {
            if (levelCompleted == unlockedLevels) {
                unlockedLevels = unlockedLevels + 1;
                try {
                    FileWriter toUpdate = new FileWriter(PROFILES_PATH + GameGUIManager.getCurrentProfile() + ".txt");
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
            File toGetLevelsFrom = new File(PROFILES_PATH + GameGUIManager.getCurrentProfile() + ".txt");
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
        layout.getChildren().add(LEVEL_1);
        layout.getChildren().add(LEVEL_2);
        layout.getChildren().add(LEVEL_3);
        layout.getChildren().add(LEVEL_4);
        layout.getChildren().add(LEVEL_5);
        layout.getChildren().add(LEVEL_1_LEADERBOARDS);
        return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void setButtonProperties() {
        WINDOW_HEADER.setStyle("-fx-font: 12 arial;");
        WINDOW_HEADER.setTranslateY(-120);
        BACK_BUTTON.setTranslateY(100);
        BACK_BUTTON.setOnAction(this);
        DELETE_PROFILE.setTranslateY(100);
        DELETE_PROFILE.setTranslateX(100);
        DELETE_PROFILE.setOnAction(this);
        LEVEL_1_LEADERBOARDS.setTranslateY(-90);
        LEVEL_1_LEADERBOARDS.setTranslateX(100);
        LEVEL_1_LEADERBOARDS.setOnAction(this);
        LEVEL_1.setTranslateY(-90);
        LEVEL_1.setOnAction(this);
        LEVEL_2.setTranslateY(-50);
        LEVEL_2.setOnAction(this);
        LEVEL_3.setTranslateY(-10);
        LEVEL_3.setOnAction(this);
        LEVEL_4.setTranslateY(30);
        LEVEL_4.setOnAction(this);
        LEVEL_5.setTranslateY(70);
        LEVEL_5.setOnAction(this);
    }

    /**
     * Handles what happens when each button is pressed in the window
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        window = ((Stage) BACK_BUTTON.getScene().getWindow());
        if(event.getSource() == BACK_BUTTON) {
            GameGUIManager.isProfileSelectorWindowNext = true;
            window.close();
            GameGUIManager.windowChange();
        } else if (event.getSource() == DELETE_PROFILE) {
            if (checkDelete() == ButtonType.OK) {
                Game.deleteProfile(GameGUIManager.getCurrentProfile());
                GameGUIManager.isProfileSelectorWindowNext = true;
                window.close();
                GameGUIManager.windowChange();
            }
        } else if (event.getSource() == LEVEL_1_LEADERBOARDS) {
            leaderBoardDisplayBox("level1");
        } else {
            leaderboardOrLevel(event);
        }
    }

    /**
     * Method that deals with the window for the leaderboard
     *
     * @param event the current event occuring in the window
    */
    public void leaderboardOrLevel(ActionEvent event) {
        if(event.getSource() == LEVEL_1 || event.getSource() == LEVEL_2) {
            String levelToLoad = loadLevel(event.getSource());
            GameGUIManager.setCurrentLevel(levelToLoad);
            GameGUIManager.isBoardWindowNext = true;
            window.close();
            GameGUIManager.windowChange();
        }
    }


    /**
     * Method that handles the loading of a level
     *
     * @return the specified level 
    */
    public String loadLevel(Object levelPressed) {
        String level = "";
        if (LEVEL_1.equals(levelPressed)) {
            level = "level1";
        } else if (LEVEL_2.equals(levelPressed)) {
            level = "level2";
        }
        return level;
    }

    /**
     * Method that enables the next level button for allowed levels
    */
    public static void buttonEnable() {
        if(unlockedLevels >= 2) {
            LEVEL_2.setDisable(false);
        }
        if (unlockedLevels >= 3) {
            LEVEL_3.setDisable(false);
        }
        if (unlockedLevels >= 4) {
            LEVEL_4.setDisable(false);
        }
        if (unlockedLevels >= 5) {
            LEVEL_5.setDisable(false);;
        }
    }

    /**
     * Method that disnables the next level button for levels that haven't been unlocked
    */
    public void buttonDisable() {
        if(unlockedLevels < 2) {
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
    }

    /**
     * Method that alerts a user of the consequences of deleting a profile
     *
     * @return the result of the alertbox
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
     * Method that retrieves the leaderboard of the game
     *
     * @return the leaderboard with the players respective positions
    */
    public String[] getLeaderBoard(String level) {
        String[] leaderboardPlaces = new String[10];
        try {
            File leaderboard = new File(LEADERBOARDS_PATH + level + ".txt");
            Scanner fileReader = new Scanner(leaderboard);
            for(int i = 0; i < leaderboardPlaces.length; i++) {
                if(fileReader.hasNext()) {
                    leaderboardPlaces[i] = fileReader.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaderboardPlaces;
    }

    public void leaderBoardDisplayBox(String level) {
        Alert leaderboardPopup = new Alert(Alert.AlertType.INFORMATION);
        leaderboardPopup.getDialogPane().setPrefSize(480, 320);
        leaderboardPopup.setTitle("LeaderBoard");
        leaderboardPopup.setHeaderText("Here is the leaderboard for " + level);
        String[] leaderboardPlaces = getLeaderBoard(level);
        String textToDisplay = "User name:        Score: \n";
        for (int i = 0; i < leaderboardPlaces.length; i++) {
            if(leaderboardPlaces[i] != null) {
                String[] line = leaderboardPlaces[i].split(" ");
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
