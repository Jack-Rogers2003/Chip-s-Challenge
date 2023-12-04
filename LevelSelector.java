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
import java.util.Scanner;

/**
 * Class for the Level selector and anything relating to its window
 * Handles the buttons, pop-ups and any calls needed for the window to function
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
    private static final String LEADERBOARDS_PATH = CURRENT_DIRECTORY + "\\Leaderboards\\";
    private static final Button DELETE_PROFILE = new Button("Delete Profile");
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static Stage window;

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
     * Generates a window and all of it's properties
     * @return A scene that is all Buttons and text needed for the window
     */
    public Scene generateMenu() {
        setButtonProperties();
        StackPane layout = new StackPane();
        buttonEnableOrDisable();
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
        LEVEL_3.setTranslateY(-10);
        LEVEL_4.setTranslateY(30);
        LEVEL_5.setTranslateY(70);
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

    public void leaderboardOrLevel(ActionEvent event) {
        if(event.getSource() == LEVEL_1) {
            String levelToLoad = loadLevel(event.getSource());
            GameGUIManager.setCurrentLevel(levelToLoad);
            GameGUIManager.isBoardWindowNext = true;
            window.close();
            GameGUIManager.windowChange();
        }
    }


    public String loadLevel(Object levelPressed) {
        String level = "";
        if (LEVEL_1.equals(levelPressed)) {
            level = "level1";
        } else if (LEVEL_2.equals(levelPressed)) {
            level = "level2";
        }
        return level;
    }
    public void buttonEnableOrDisable() {
        int levelsCompleted = Game.getCompletedLevels(GameGUIManager.
                getCurrentProfile());
        if(levelsCompleted < 2) {
            LEVEL_2.setDisable(true);
        }
        if (levelsCompleted < 3) {
            LEVEL_3.setDisable(true);
        }
        if (levelsCompleted < 4) {
            LEVEL_4.setDisable(true);
        }
        if (levelsCompleted < 5) {
            LEVEL_5.setDisable(true);
        }
    }

    public ButtonType checkDelete() {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setTitle("Profile Deletion Confirm");
        alertBox.setHeaderText("Delete this profile?" +
                " You will not be able to recover it");
        alertBox.showAndWait();
        return alertBox.getResult();
    }

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
