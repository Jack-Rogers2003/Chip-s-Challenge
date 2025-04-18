import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Handles all GUI elements of the profile selection window
 * @author Jack Rogers
 * @version 1.3
 */
public class ProfileSelectorWindow extends Application implements
        EventHandler<ActionEvent> {
    //Button ot create a new profile
    private static final Button NEW_PROFILE_BUTTON =
            new Button("New Profile");
    //Button to load a profile
    private static final Button LOAD_PROFILE_BUTTON
            = new Button("Load Profile");
    //Button to exit the window
    private static final Button EXIT_BUTTON = new Button("Exit");
    //Header for the window
    private static final Text WINDOW_HEADER = new Text("Choose an option");
    //X translation of the new profile button
    private static final int PROFILE_BUTTON_X_TRANSLATION = 50;
    //X translation of the Load Profile button
    private static final int LOAD_PROFILE_BUTTON_X_TRANSLATION = -40;
    //Y translation of the Exit button
    private static final int EXIT_BUTTON_Y_TRANSLATION = 50;
    //Y translation of the window header
    private static final int WINDOW_HEADER_Y_TRANSLATION = -50;
    //instance of Game
    private static final Game GAME = new Game();
    //Height of the window
    private static final int WINDOW_HEIGHT = 250;
    //width of the window
    private static final int WINDOW_WIDTH = 250;

    /**
     *
     * @param profileSelectionWindow the primary stage for this application,
     * onto which the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage profileSelectionWindow) {

    }

    /**
     * Creates the window and any associated properties
     * @return A scene that contains all created menu properties
     */
    public Scene generateMenu() {
        NEW_PROFILE_BUTTON.setTranslateX(PROFILE_BUTTON_X_TRANSLATION);
        LOAD_PROFILE_BUTTON.setTranslateX(LOAD_PROFILE_BUTTON_X_TRANSLATION);
        EXIT_BUTTON.setTranslateY(EXIT_BUTTON_Y_TRANSLATION);
        NEW_PROFILE_BUTTON.setOnAction(this);
        LOAD_PROFILE_BUTTON.setOnAction(this);
        EXIT_BUTTON.setOnAction(this);
        WINDOW_HEADER.setStyle("-fx-font: 24 arial;");
        WINDOW_HEADER.setTranslateY(WINDOW_HEADER_Y_TRANSLATION);
        StackPane layout = new StackPane();
        layout.getChildren().add(NEW_PROFILE_BUTTON);
        layout.getChildren().add(LOAD_PROFILE_BUTTON);
        layout.getChildren().add(EXIT_BUTTON);
        layout.getChildren().add(WINDOW_HEADER);
        return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Handles all button presses and their associated actions
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        Stage window = ((Stage) EXIT_BUTTON.getScene().getWindow());
        if (event.getSource() == LOAD_PROFILE_BUTTON) {
            String selectedName = nameSelectionPopUp(GAME.getAllPlayerNames());
            if (GAME.getAllPlayerNames().contains(selectedName)) {
                GameGUIManager.setCurrentProfile(selectedName);
                GameGUIManager.setIsLevelSelectorWindowNext();
                window.close();
                GameGUIManager.windowChange();
            }
        } else if (event.getSource() == NEW_PROFILE_BUTTON) {
            String userName = nameEntryPopUp();
            try {
                if (userName.length() > 0) {
                    confirmationBox(GAME.createNewPlayerProfile(userName));
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            window.close();
        }
    }

    /**
     * Pop up box for when a Profile has been created
     * @param userName the name of the profile created, is displayed in the
     *                 pop-up
     */
    public void confirmationBox(String userName) {
        Alert confirmationPopUp = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationPopUp.setTitle("Success");
        confirmationPopUp.setHeaderText("Profile " + userName +
                " successfully made!");
        confirmationPopUp.show();
    }

    /**
     * A pop-up to get the wanted name of the new profile
     * @return A string that is the wanted username of the new profile
     */
    public String nameEntryPopUp() {
        TextInputDialog playerNameEntry = new TextInputDialog();
        playerNameEntry.setTitle("Profile name entry");
        playerNameEntry.setHeaderText("Enter your wanted name: ");
        playerNameEntry.setContentText("Name: ");
        Optional<String> result = playerNameEntry.showAndWait();
        return result.orElse("");
    }

    /**
     * Pop-up that presents a selection box of profiles that can be used for
     * the game
     * @param profileNames The names of all profiles created, it is displayed
     *                     in a selection box
     * @return A string that is the username of the selected profile
     */
    public String nameSelectionPopUp(ArrayList<String> profileNames) {
        ChoiceDialog<String> selectProfile = new ChoiceDialog<>(
                "Select Name", profileNames);
        selectProfile.setTitle("Select your profile");
        selectProfile.setHeaderText("All profiles");
        Optional<String> result = selectProfile.showAndWait();
        return result.orElse("");
    }
}
