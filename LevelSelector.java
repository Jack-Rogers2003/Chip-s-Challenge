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

import java.io.FileNotFoundException;


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
    private static final Button DELETE_PROFILE = new Button("Delete Profile");


    /**
     * @param levelSelectWindow the primary stage for this application, onto which
     * the application scene can be set.
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
        WINDOW_HEADER.setStyle("-fx-font: 12 arial;");
        WINDOW_HEADER.setTranslateY(-120);
        BACK_BUTTON.setTranslateY(100);
        BACK_BUTTON.setOnAction(this);
        DELETE_PROFILE.setTranslateY(100);
        DELETE_PROFILE.setTranslateX(100);
        DELETE_PROFILE.setOnAction(this);
        LEVEL_1.setTranslateY(-90);
        LEVEL_2.setTranslateY(-50);
        LEVEL_3.setTranslateY(-10);
        LEVEL_4.setTranslateY(30);
        LEVEL_5.setTranslateY(70);
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
        return new Scene(layout, 300, 250);
    }

    /**
     * Handles what happens when each button is pressed in the window
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        Stage window = ((Stage) BACK_BUTTON.getScene().getWindow());
        if(event.getSource() == BACK_BUTTON) {
            GameGUIManager.isProfileSelectorWindowNext = true;
            window.close();
            GameGUIManager.windowChange();
        } else if (event.getSource() == DELETE_PROFILE) {
            if(checkDelete() == ButtonType.OK) {
                Game.deleteProfile(GameGUIManager.getCurrentProfile());
                GameGUIManager.isProfileSelectorWindowNext = true;
                window.close();
                GameGUIManager.windowChange();
            }
        }
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
        alertBox.setHeaderText("Are you sure you want to delete this profile?" +
                " You will not be able to recover it");
        alertBox.showAndWait();
        return alertBox.getResult();
    }

}
