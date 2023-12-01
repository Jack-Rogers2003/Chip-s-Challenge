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
import java.util.Objects;
import java.util.Optional;

/**
 * Handles all GUI elements of the profile selection window
 */
public class ProfileSelectorWindow extends Application implements EventHandler<ActionEvent> {
    private static final Button NEW_PROFILE_BUTTON = new Button("New Profile");
    private static final Button LOAD_PROFILE_BUTTON
            = new Button("Load Profile");
    private static final Button EXIT_BUTTON = new Button("Exit");
    private static final Text WINDOW_HEADER = new Text("Choose an option");
    private static final Game GAME = new Game();
    private static final int WINDOW_HEIGHT = 250;
    private static final int WINDOW_WIDTH = 500;

    /**
     *
     * @param profileSelectionWindow the primary stage for this application, onto which
     * the application scene can be set.
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
        NEW_PROFILE_BUTTON.setTranslateX(50);
        LOAD_PROFILE_BUTTON.setTranslateX(-40);
        EXIT_BUTTON.setTranslateY(50);
        NEW_PROFILE_BUTTON.setOnAction(this);
        LOAD_PROFILE_BUTTON.setOnAction(this);
        EXIT_BUTTON.setOnAction(this);
        WINDOW_HEADER.setStyle("-fx-font: 24 arial;");
        WINDOW_HEADER.setTranslateY(-50);
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
        if(event.getSource() == LOAD_PROFILE_BUTTON) {
            String selectedName = nameSelectionPopUp(GAME.getAllPlayerNames());
            if(GAME.getAllPlayerNames().contains(selectedName)) {
                GameGUIManager.setCurrentProfile(selectedName);
                GameGUIManager.isLevelSelectorWindowNext = true;
                window.close();
                GameGUIManager.windowChange();
            }
        } else if (event.getSource() == NEW_PROFILE_BUTTON) {
            String userName = nameEntryPopUp();
            try {
                confirmationBox(GAME.createNewPlayerProfile(userName));
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
        ChoiceDialog<String> selectProfile = new ChoiceDialog<>("Select Name", profileNames);
        selectProfile.setTitle("Select your profile");
        selectProfile.setHeaderText("All profiles");
        Optional<String> result = selectProfile.showAndWait();
        return result.orElse("");
    }
}
